// script.js (updated)
let userId = localStorage.getItem("userId");
if (!userId) {
  // If there is no userId in localStorage, redirect back to login:
  window.location.href = "../login.html";
} else {
  userId = parseInt(userId, 10);
}

let contacts = [];
const contactList = document.querySelector('.contact-list');
const addTagButton = document.getElementById('addTagButton');
const addContactButton = document.getElementById('addContactButton');
const customTagsDiv = document.getElementById('customTags');
const sortButtons = document.querySelectorAll('.sort-button'); 
const searchInput = document.getElementById('searchInput');
const searchButton = document.getElementById('searchButton');
let sortAscending = true;


const nameRegex = /^[A-Za-zÇÖÜĞİŞçöüğiş\s]+$/u;
const phoneRegex = /^\+(?:90|1|44|49|33|34)\s\d{3}\s\d{3}\s\d{2}\s\d{2}$/;
const emailRegex = /^[^\s@]+@[^\s@]+\.(com|net|org|edu|gov|io|co)$/i;

// unchanged helper for prompting + validation
function promptValidated(message, validator, errorMsg) {
  while (true) {
    const val = prompt(message);
    if (val === null) return null;
    if (validator(val.trim())) return val.trim();
    alert(errorMsg);
  }
}

// --- UPDATED: loadContacts now calls the Flask API instead of a local file ---
async function loadContacts() {
  try {
    // Fetch only this one user’s contacts:
    const res = await fetch(`http://127.0.0.1:5000/contacts/${userId}`);
    if (!res.ok) {
      throw new Error(`User ${userId} fetch failed: ${res.status}`);
    }
    const data = await res.json();
    if (data.status !== 'success') {
      throw new Error(`User ${userId} error: ${data.message}`);
    }

    // Map each contact so we keep tag‐objects as before:
    contacts = data.contacts.map(c => ({
      contact_id: c.contact_id,
      name: c.name,
      phone: c.phone,
      email: c.email,
      address: c.address,
      tags: c.tags || []
    }));

    renderContacts();
  }
  catch (err) {
    console.error(err);
    alert('Kişiler yüklenirken hata oluştu: ' + err.message);
  }
}




// --- UPDATED: renderContacts now assumes tags is an array of strings (converted above) ---
function renderContacts() {
  contactList.innerHTML = '';

  contacts.forEach((c, idx) => {
    const li = document.createElement('li');
    li.className = 'contact-card';
    li.dataset.index = idx;

    // Build the static part of the contact card:
    li.innerHTML = `
      <h2>${c.name}</h2>
      <button class="edit-button">Düzenle</button>
      <button class="delete-button">✕</button>
      <p>📞 ${c.phone}</p>
      <p>📧 ${c.email}</p>
      <p>🏠 ${c.address || ''}</p>
      <div class="contact-tags-container"></div>
    `;

    // Edit/Delete listeners for the contact
    li.querySelector('.edit-button').addEventListener('click', () => editContact(idx));
    li.querySelector('.delete-button').addEventListener('click', () => deleteContact(idx));

    const tagsContainer = li.querySelector('.contact-tags-container');

    // 1) Render existing tags for this contact:
    c.tags.forEach(tagObj => {
      // tagObj: { tag_id, tag_name }
      const tagDiv = document.createElement('div');
      tagDiv.className = 'contact-tag';
      tagDiv.dataset.tagId = tagObj.tag_id;

      const spanText = document.createElement('span');
      spanText.className = 'tag-text';
      spanText.textContent = tagObj.tag_name;

      const btnUnassign = document.createElement('button');
      btnUnassign.className = 'tag-unassign';
      btnUnassign.textContent = '✕';

      tagDiv.appendChild(spanText);
      tagDiv.appendChild(btnUnassign);
      tagsContainer.appendChild(tagDiv);

      // Unassign on ❌ click:
      btnUnassign.addEventListener('click', async () => {
        if (!confirm(`"${tagObj.tag_name}" etiketini bu kişiden çıkarmak istediğinizden emin misiniz?`))
          return;
        try {
          const response = await fetch(`http://127.0.0.1:5000/contacts/unassign_tag/${userId}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ contact_id: c.contact_id, tag_id: tagObj.tag_id })
          });
          if (!response.ok) {
            const errText = await response.text();
            throw new Error(`Etiket silme hatası: ${response.status} → ${errText}`);
          }
          const dataUn = await response.json();
          if (dataUn.error) {
            throw new Error(dataUn.error);
          }
          // Remove from local c.tags, then re-render
          c.tags = c.tags.filter(t => t.tag_id !== tagObj.tag_id);
          renderContacts();
        }
        catch (err) {
          console.error(err);
          alert('Etiket çıkarılırken hata: ' + err.message);
        }
      });
    });

    // 2) Add a “+” button after existing tags:
    const plusBtn = document.createElement('button');
    plusBtn.className = 'tag-add-btn';
    plusBtn.textContent = '+';
    tagsContainer.appendChild(plusBtn);

    // Hidden container for “pick a tag to add”:
    const pickerDiv = document.createElement('div');
    pickerDiv.className = 'contact-tag-picker';
    pickerDiv.style.display = 'none';
    tagsContainer.appendChild(pickerDiv);

    // Populate the picker with allTags NOT yet in c.tags:
    function populatePicker() {
      pickerDiv.innerHTML = '';
      // Build a set of existing tag IDs for this contact:
      const assignedIds = new Set(c.tags.map(t => t.tag_id));
      allTags.forEach(t => {
        if (!assignedIds.has(t.id)) {
          const pickTag = document.createElement('div');
          pickTag.className = 'picker-tag-item';
          pickTag.dataset.tagId = t.id;
          pickTag.textContent = t.tag_name;

          // Click → assign this tag
          pickTag.addEventListener('click', async () => {
            try {
              const response = await fetch(`http://127.0.0.1:5000/contacts/assign_tag/${userId}`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ contact_id: c.contact_id, tag_id: t.id })
              });
              if (!response.ok) {
                const errText = await response.text();
                throw new Error(`Etiket atama hatası: ${response.status} → ${errText}`);
              }
              const dataAss = await response.json();
              if (dataAss.error) {
                throw new Error(dataAss.error);
              }
              // On success: add to c.tags and re-render
              c.tags.push({ tag_id: t.id, tag_name: t.tag_name });
              renderContacts();
            }
            catch (err) {
              console.error(err);
              alert('Etiket eklenirken hata: ' + err.message);
            }
          });

          pickerDiv.appendChild(pickTag);
        }
      });
    }

    // 3) Click “+” toggles the picker:
    plusBtn.addEventListener('click', () => {
      if (pickerDiv.style.display === 'none') {
        populatePicker();
        pickerDiv.style.display = 'flex';
      } else {
        pickerDiv.style.display = 'none';
      }
    });

    contactList.appendChild(li);
  });
}



// --- The rest of your edit/delete/add UI logic can remain unchanged, 
//     since you’re still mutating the in-memory `contacts` array locally --- 

async function editContact(idx) {
  const c = contacts[idx];
  // 1) Prompt for basic fields only:
  const name = promptValidated(
    `İsim (mevcut: ${c.name})(); s. harf ve boşluk):`,
    v => nameRegex.test(v),
    'Geçersiz isim.'
  );
  if (name === null) return;

  const phone = promptValidated(
    `Telefon (mevcut: ${c.phone})(); +90 123 456 78 90):`,
    v => phoneRegex.test(v),
    'Geçersiz telefon.'
  );
  if (phone === null) return;

  const email = promptValidated(
    `Email (mevcut: ${c.email})(); örn: biri@birisi.com):`,
    v => emailRegex.test(v),
    'Geçersiz email.'
  );
  if (email === null) return;

  const address = promptValidated(
    `Adres (mevcut: ${c.address || 'boş'}):`,
    v => true,
    ''
  );
  if (address === null) return;

  // 2) Build payload & send update (no tags here)
  const payload = {
    contact_id: c.contact_id,
    name: name,
    phone: phone,
    email: email,
    address: address
  };

  try {
    const resUpdate = await fetch(`http://127.0.0.1:5000/contacts/update/${userId}`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    });
    if (!resUpdate.ok) {
      const errText = await resUpdate.text();
      throw new Error(`Güncelleme hatası: ${resUpdate.status} → ${errText}`);
    }
    const dataUpdate = await resUpdate.json();
    if (dataUpdate.error) throw new Error(dataUpdate.error);

    // 3) Update local contact & re-render
    c.name = dataUpdate.contact.name;
    c.phone = dataUpdate.contact.phone;
    c.email = dataUpdate.contact.email;
    c.address = dataUpdate.contact.address;
    renderContacts();
    alert('Kişi başarıyla güncellendi.');
  } catch (err) {
    console.error(err);
    alert('Güncelleme sırasında hata: ' + err.message);
  }
}


async function deleteContact(idx) {
  // 1) Find the contact object by index:
  const c = contacts[idx];
  const shouldDelete = confirm(`${c.name} adlı kişiyi silmek istediğinizden emin misiniz?`);
  if (!shouldDelete) return;

  try {
    const payload = { contact_id: c.contact_id };

    const res = await fetch(`http://127.0.0.1:5000/contacts/${userId}`, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(payload)
    });

    if (!res.ok) {
      const errText = await res.text();
      throw new Error(`Silme isteği başarısız: ${res.status} → ${errText}`);
    }

    const data = await res.json();
    // Backend’in delete_contact() fonksiyonu, başarılıysa muhtemelen:
    // { status: "success", message: "Contact deleted successfully." }
    if (data.status === 'error' || data.error) {
      // Eğer hata formatınız { error: "…"} ise bunu da yakalayabilirsiniz:
      throw new Error(data.message || data.error || 'Silme sırasında hata oluştu.');
    }

    // 2) Sunucu tarafı başarıyla silindi → front-end dizisinden de kaldır:
    contacts.splice(idx, 1);

    // 3) Listeyi yeniden çiz:
    renderContacts();
    alert("Kişi başarıyla silindi.");
  }
  catch (err) {
    console.error(err);
    alert("Silme başarısız: " + err.message);
  }
}

function addCustomTag() {
  const tag = promptValidated('Yeni etiket adı:', v => v.length > 0, 'Etiket boş olamaz.');
  if (tag === null) return;
  if (![...customTagsDiv.children].some(el => el.textContent === tag)) {
    const span = document.createElement('span');
    span.className = 'tag';
    span.textContent = tag;
    customTagsDiv.appendChild(span);
  }
}

// script.js

async function addContact() {
  // 1) Prompt for basic contact fields (as before)
  const name = promptValidated(
    'Yeni kişi ismi (sadece harf ve boşluk):',
    v => nameRegex.test(v),
    'Geçersiz isim.'
  );
  if (name === null) return;

  const phone = promptValidated(
    'Telefon (+90 123 456 78 90):',
    v => phoneRegex.test(v),
    'Geçersiz telefon.'
  );
  if (phone === null) return;

  const email = promptValidated(
    'Email (örnek: biri@birisi.com):',
    v => emailRegex.test(v),
    'Geçersiz email.'
  );
  if (email === null) return;

  const address = promptValidated('Adres:', v => true, '');
  if (address === null) return;

  // ──────────────────────────────────────────────────────
  // 2) Fetch ALL existing tags for this user so we can match names
  // ──────────────────────────────────────────────────────
  let allTags = [];
  try {
    const resTags = await fetch(`http://127.0.0.1:5000/tags/${userId}`);
    if (!resTags.ok) {
      throw new Error(`Etiketler alınamadı: ${resTags.status}`);
    }
    const dataTags = await resTags.json();
    if (dataTags.status !== 'success') {
      throw new Error(dataTags.message);
    }
    // dataTags.tags is an array of { id, user_id, tag_name }
    allTags = dataTags.tags;
  } catch (err) {
    console.error(err);
    alert('Etiketler yüklenirken hata: ' + err.message);
    return;
  }

  // Build a name→ID lookup (case-insensitive)
  const tagNameToId = {};
  allTags.forEach(t => {
    tagNameToId[t.tag_name.toLowerCase()] = t.id;
  });

  // ──────────────────────────────────────────────────────
  // 3) Prompt for tag NAMES (comma-separated). If blank → no tags.
  //    Otherwise, re-prompt until every name is valid.
  // ──────────────────────────────────────────────────────
  let newTagNames = [];
  while (true) {
    const tagsStr = promptValidated(
      'Etiket isimleri (virgülle ayır; örnek: "iş, arkadaş"). Boşsa hiçbir etiket yok:',
      v => true,
      ''
    );
    if (tagsStr === null) {
      // User cancelled the flow
      return;
    }

    // Split by comma, trim, drop empty
    newTagNames = tagsStr
      .split(',')
      .map(t => t.trim())
      .filter(t => t);

    // If user left it blank, they want NO tags → accept
    if (newTagNames.length === 0) {
      break;
    }

    // Otherwise, check that every entered name exists in tagNameToId
    const invalids = newTagNames.filter(
      name => !(name.toLowerCase() in tagNameToId)
    );
    if (invalids.length > 0) {
      alert(
        `Aşağıdaki etiket(ler) veri tabanında bulunamadı: ${invalids.join(', ')}\n` +
        'Lütfen var olan etiket adlarını kullanın, ya da boş bırakın.'
      );
      // Loop again until all names are valid (or blank)
    } else {
      // All provided names are valid → break out
      break;
    }
  }

  // Convert valid tag names → IDs:
  const tagIds = newTagNames.map(name => tagNameToId[name.toLowerCase()]);

  // ──────────────────────────────────────────────────────
  // 4) Create the new contact (no tags yet)
  // ──────────────────────────────────────────────────────
  const payload = { name, phone, email, address };
  let newContact;
  try {
    const resCreate = await fetch(
      `http://127.0.0.1:5000/contacts/create/${userId}`,
      {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      }
    );
    if (!resCreate.ok) {
      const errText = await resCreate.text();
      throw new Error(`Kişi oluşturulamadı: ${resCreate.status} → ${errText}`);
    }
    const dataCreate = await resCreate.json();
    if (dataCreate.status !== 'success') {
      throw new Error(dataCreate.message || 'Kişi oluşturulamadı.');
    }

    newContact = dataCreate.contact; // { contact_id, ... }
  } catch (err) {
    console.error(err);
    alert('Kişi eklenirken hata: ' + err.message);
    return;
  }

  // ──────────────────────────────────────────────────────
  // 5) If there are any valid tag IDs, assign them one by one
  // ──────────────────────────────────────────────────────
  if (tagIds.length > 0) {
    try {
      const assignPromises = tagIds.map(tagId =>
        fetch(`http://127.0.0.1:5000/contacts/assign_tag/${userId}`, {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            contact_id: newContact.contact_id,
            tag_id: tagId
          })
        }).then(resAssign => {
          if (!resAssign.ok) {
            return resAssign.text().then(txt => {
              throw new Error(`Etiket id=${tagId} atama hatası: ${resAssign.status} → ${txt}`);
            });
          }
          return resAssign.json();
        })
      );
      await Promise.all(assignPromises);
    } catch (err) {
      console.error(err);
      alert('Etiket atama sırasında hata: ' + err.message);
      // (Optionally: you could decide to continue or rollback)
      return;
    }
  }

  // ──────────────────────────────────────────────────────
  // 6) Finally, reload and re-render so that new contact + its tags appear
  // ──────────────────────────────────────────────────────
  await loadContacts();
  renderContacts();
  alert('Kişi ve etiketler başarıyla eklendi.');
}


// Don't forget to wire it up:
//addContactButton.addEventListener('click', addContact);
function renderGivenContacts(list) {
  contactList.innerHTML = '';
  list.forEach((c, idx) => {
    const li = document.createElement('li');
    li.className = 'contact-card';
    li.dataset.index = idx;
    li.innerHTML = `
      <h2>${c.name}</h2>
      <button class="edit-button">Düzenle</button>
      <button class="delete-button">✕</button>
      <p>📞 ${c.phone}</p>
      <p>📧 ${c.email}</p>
      <p>🏠 ${c.address || ''}</p>
      ${c.tags.map(t => `<span class="tag">${t}</span>`).join('')}
    `;
    li.querySelector('.edit-button').addEventListener('click', () => editContact(idx));
    li.querySelector('.delete-button').addEventListener('click', () => deleteContact(idx));
    contactList.appendChild(li);
  });
}

function performSearch() {
  const query = searchInput.value.trim().toLowerCase();
  if (!query) {
    // If the query is empty, show all contacts
    renderContacts();
    return;
  }

  // Filter the global `contacts` array:
  const filtered = contacts.filter(c => {
    return (
      c.name.toLowerCase().includes(query) ||
      c.phone.replace(/\s+/g, '').includes(query.replace(/\s+/g, '')) ||
      c.email.toLowerCase().includes(query) ||
      c.address.toLowerCase().includes(query)
    );
  });

  renderGivenContacts(filtered);
}

sortButtons.forEach(btn => {
  btn.addEventListener('click', async () => {
    // 1) Reload the latest contacts from the server
    await loadContacts();

    // 2) Normalize phone digits for each contact once
    //    (we’ll strip all non-digits so "+90 535 070 24 94" → "905350702494")
    contacts.forEach(c => {
      c._phoneDigits = (c.phone || '').replace(/\D/g, '');
    });

    // 3) Decide sort behavior based on the button’s label:
    const label = btn.textContent.trim().toUpperCase();

    switch (label) {
      case 'A-Z':
        // Sort by name ascending (Turkish locale, base sensitivity)
        contacts.sort((a, b) =>
          a.name.localeCompare(b.name, 'tr', { sensitivity: 'base' })
        );
        break;

      case 'Z-A':
        // Same as A-Z, then reverse
        contacts.sort((a, b) =>
          a.name.localeCompare(b.name, 'tr', { sensitivity: 'base' })
        );
        contacts.reverse();
        break;

      case '0-9':
        // Sort by numeric phone ascending
        contacts.sort((a, b) => {
          // Compare as strings of equal length—localeCompare works if they share the same digit count.
          // If you prefer numeric compare: parseInt(c._phoneDigits) but for very long numbers it can overflow.
          return a._phoneDigits.localeCompare(b._phoneDigits, undefined, { numeric: true });
        });
        break;

      case '9-0':
        // Sort by numeric phone descending
        contacts.sort((a, b) => {
          return a._phoneDigits.localeCompare(b._phoneDigits, undefined, { numeric: true });
        });
        contacts.reverse();
        break;

      default:
        // If there’s any other “sort-button,” do nothing
        return;
    }

    // 4) Re-render the (now-sorted) list
    renderContacts();
  });
});

async function addTagToDatabase() {
  const tagName = promptValidated(
    'Yeni etiket adı:',
    v => v.trim().length > 0,
    'Etiket boş olamaz.'
  );
  if (tagName === null) return;

  try {
    const response = await fetch(
      `http://127.0.0.1:5000/tags/create/${userId}`,
      {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ tag_name: tagName })
      }
    );
    if (!response.ok) {
      const errText = await response.text();
      throw new Error(`Etiket oluşturulamadı: ${response.status} → ${errText}`);
    }
    const data = await response.json();
    if (data.error) throw new Error(data.error);

    // Wrap the new tag exactly as loadTags() does:
    const newTag = data.tag; // { id, user_id, tag_name }
    const container = document.createElement('div');
    container.className = 'tag-item';
    container.dataset.tagId = newTag.id;

    const spanText = document.createElement('span');
    spanText.className = 'tag-text';
    spanText.textContent = newTag.tag_name;

    const btnDelete = document.createElement('button');
    btnDelete.className = 'tag-delete';
    btnDelete.textContent = '✕';

    container.appendChild(spanText);
    container.appendChild(btnDelete);
    customTagsDiv.appendChild(container);

    // 1) Toggle selected on click:
    spanText.addEventListener('click', () => {
      container.classList.toggle('selected');
      filterContactsBySelectedTags();
    });
    
    // 2) Delete on ❌ click:
    btnDelete.addEventListener('click', async () => {
      if (!confirm(`"${newTag.tag_name}" etiketini silmek istediğinizden emin misiniz?`))
        return;
      try {
        const delRes = await fetch(`http://127.0.0.1:5000/tags/${userId}`, {
          method: 'DELETE',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({ tag_id: newTag.id })
        });
        if (!delRes.ok) {
          const errText = await delRes.text();
          throw new Error(`Etiket silme hatası: ${delRes.status} → ${errText}`);
        }
        const delData = await delRes.json();
        if (delData.status === 'error' || delData.error) {
          throw new Error(delData.message || delData.error);
        }
        container.remove();
      }
      catch (err) {
        console.error(err);
        alert('Etiket silinirken hata: ' + err.message);
      }
    });

    alert('Etiket başarıyla eklendi.');
  }
  catch (err) {
    console.error(err);
    alert('Etiket eklenirken hata oluştu: ' + err.message);
  }
}

async function loadTags() {
  try {
    const res = await fetch(`http://127.0.0.1:5000/tags/${userId}`);
    if (!res.ok) throw new Error(`Etiketler alınamadı: ${res.status}`);
    const data = await res.json();
    if (data.status !== 'success') throw new Error(data.message);

    // Store them in our global array:
    allTags = data.tags; // each is { id, user_id, tag_name }

    // Render the global tag list in customTagsDiv:
    customTagsDiv.innerHTML = '';
    data.tags.forEach(t => {
      const container = document.createElement('div');
      container.className = 'tag-item';
      container.dataset.tagId = t.id;

      const spanText = document.createElement('span');
      spanText.className = 'tag-text';
      spanText.textContent = t.tag_name;

      const btnDelete = document.createElement('button');
      btnDelete.className = 'tag-delete';
      btnDelete.textContent = '✕';

      container.appendChild(spanText);
      container.appendChild(btnDelete);
      customTagsDiv.appendChild(container);

      // Toggle selection + filter on click
      spanText.addEventListener('click', () => {
        container.classList.toggle('selected');
        filterContactsBySelectedTags();
      });

      // Delete from DB + DOM
      btnDelete.addEventListener('click', async () => {
        if (!confirm(`"${t.tag_name}" etiketini silmek istediğinizden emin misiniz?`))
          return;
        try {
          const response = await fetch(`http://127.0.0.1:5000/tags/${userId}`, {
            method: 'DELETE',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ tag_id: t.id })
          });
          if (!response.ok) {
            const errText = await response.text();
            throw new Error(`Etiket silme hatası: ${response.status} → ${errText}`);
          }
          const dataDel = await response.json();
          if (dataDel.status === 'error' || dataDel.error) {
            throw new Error(dataDel.message || dataDel.error);
          }
          container.remove();
          // Update our local allTags array:
          allTags = allTags.filter(tag => tag.id !== t.id);
          filterContactsBySelectedTags();
        }
        catch (err) {
          console.error(err);
          alert('Etiket silinirken hata: ' + err.message);
        }
      });
    });
  }
  catch (err) {
    console.error(err);
    alert('Etiketler yüklenirken hata oluştu: ' + err.message);
  }
}


async function unassignTagFromContact(contactId, tagId) {
  const res = await fetch(`http://127.0.0.1:5000/contacts/unassign_tag/${userId}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ contact_id: contactId, tag_id: tagId })
  });
  if (!res.ok) {
    const txt = await res.text();
    throw new Error(`Etiket silme hatası: ${res.status} → ${txt}`);
  }
  const data = await res.json();
  if (data.error) {
    throw new Error(data.error);
  }
  // success → return nothing
}

// -------------------
// Helper: render a given list of contacts (same structure as renderContacts)
// -------------------
function renderContactList(list) {
  contactList.innerHTML = '';

  list.forEach((c, idx) => {
    const li = document.createElement('li');
    li.className = 'contact-card';
    li.dataset.index = idx;

    li.innerHTML = `
      <h2>${c.name}</h2>
      <button class="edit-button">Düzenle</button>
      <button class="delete-button">✕</button>
      <p>📞 ${c.phone}</p>
      <p>📧 ${c.email}</p>
      <p>🏠 ${c.address || ''}</p>
      <div class="contact-tags-container"></div>
    `;

    li.querySelector('.edit-button').addEventListener('click', () => editContact(idx));
    li.querySelector('.delete-button').addEventListener('click', () => deleteContact(idx));

    const tagsContainer = li.querySelector('.contact-tags-container');
    c.tags.forEach(tagObj => {
      const tagDiv = document.createElement('div');
      tagDiv.className = 'contact-tag';
      tagDiv.dataset.tagId = tagObj.tag_id;

      const spanText = document.createElement('span');
      spanText.className = 'tag-text';
      spanText.textContent = tagObj.tag_name;

      const btnUnassign = document.createElement('button');
      btnUnassign.className = 'tag-unassign';
      btnUnassign.textContent = '✕';

      tagDiv.appendChild(spanText);
      tagDiv.appendChild(btnUnassign);
      tagsContainer.appendChild(tagDiv);

      btnUnassign.addEventListener('click', async () => {
        if (!confirm(`"${tagObj.tag_name}" etiketini bu kişiden çıkarmak istediğinizden emin misiniz?`))
          return;
        try {
          const response = await fetch(
            `http://127.0.0.1:5000/contacts/unassign_tag/${userId}`,
            {
              method: 'POST',
              headers: { 'Content-Type': 'application/json' },
              body: JSON.stringify({
                contact_id: c.contact_id,
                tag_id: tagObj.tag_id
              })
            }
          );
          if (!response.ok) {
            const errText = await response.text();
            throw new Error(`Etiket silme hatası: ${response.status} → ${errText}`);
          }
          const dataUn = await response.json();
          if (dataUn.error) {
            throw new Error(dataUn.error);
          }
          // Remove from c.tags then re-render the filtered list:
          c.tags = c.tags.filter(t => t.tag_id !== tagObj.tag_id);
          filterContactsBySelectedTags();
        }
        catch (err) {
          console.error(err);
          alert('Etiket çıkarılırken hata: ' + err.message);
        }
      });
    });

    contactList.appendChild(li);
  });
}

// -------------------
// Helper: gather selected tag-IDs and filter contacts accordingly
// -------------------
function filterContactsBySelectedTags() {
  // 1) Find all tag-items in the global list that have .selected
  const selectedTagEls = Array.from(customTagsDiv.querySelectorAll('.tag-item.selected'));
  const selectedIds = selectedTagEls.map(el => parseInt(el.dataset.tagId, 10));

  if (selectedIds.length === 0) {
    // No tags selected → show all
    renderContacts();
    return;
  }

  // 2) Filter `contacts` to those that have at least one matching tag_id
  const filtered = contacts.filter(c =>
    selectedIds.every(id => c.tags.some(t => t.tag_id === id))
  );  

  // 3) Render only that subset
  renderContactList(filtered);
}



// 3) Kaldırın eski addCustomTag dinleyicisini (varsa) ve yerine yenisini ekleyin:
addTagButton.removeEventListener('click', addCustomTag); // Eğer önceden bağlandıysa
addTagButton.addEventListener('click', addTagToDatabase);

searchButton.addEventListener('click', performSearch);
searchInput.addEventListener('keyup', (e) => {
  if (e.key === 'Enter') performSearch();
});

addContactButton.addEventListener('click', addContact);
window.addEventListener('DOMContentLoaded', async () => {
  await loadContacts();
  await loadTags();      // Populate existing tags on page load

  const logoutBtn = document.getElementById('logoutBtn');
  logoutBtn.addEventListener('click', () => {
    localStorage.removeItem('userId');
    window.location.href = '../login.html';
  });

});


// 1) Grab the logout button element:
const logoutBtn = document.getElementById('logoutBtn');

// 2) When clicked, remove the saved userId and redirect to login:
logoutBtn.addEventListener('click', () => {
  // Clear the stored userId
  localStorage.removeItem('userId');

  // (Optional) You could also clear any other session data:
  // localStorage.removeItem('someOtherKey');

  // Redirect back to login page
  window.location.href = '../login.html';
});
