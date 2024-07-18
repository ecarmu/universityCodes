fetch('https://run.mocky.io/v3/4f3711a9-359d-4c98-92fb-948e5be3b431')
    .then(response => response.json())
    .then(data => {
        
        const areaCodesSelect = document.getElementById('areaCodesSelect');

        
        const initOption = document.createElement('option');
        initOption.value = '';
        initOption.classList.add('init');
        initOption.disabled = true;
        initOption.selected = true;
        initOption.textContent = 'Area code';
        areaCodesSelect.appendChild(initOption);

        
        for (const city in data.iller) {
            if (data.iller.hasOwnProperty(city)) {
                const option = document.createElement('option');
                option.value = data.iller[city];
                option.textContent = `${data.iller[city]} (${city})`;
                areaCodesSelect.appendChild(option);
            }
        }

        
        for (const operator in data.operatorler) {
            if (data.operatorler.hasOwnProperty(operator)) {
                const operatorOptions = data.operatorler[operator].map(code => {
                    const option = document.createElement('option');
                    option.value = code;
                    option.textContent = `${code} (${operator})`;
                    return option;
                });
                areaCodesSelect.append(...operatorOptions);
            }
        }
    })
    .catch(error => console.error('Error fetching data:', error));



fetch("https://run.mocky.io/v3/62c0b82a-56ee-445d-b9d7-0ab6f1812169")
    .then(response => response.json())
    .then(data => {
        var cities = data.buyuk_sehirler;

        var selectElement = document.querySelector('select[name="cities"]');
        cities.forEach(function (city) {
            var option = document.createElement('option');
            option.value = city;
            option.text = city;
            selectElement.appendChild(option);
        });
    })
    .catch(error => console.error('Error fetching cities:', error));


var courses;
fetch("https://run.mocky.io/v3/a5c14da1-89ef-485e-8cb0-523e229acaeb")
    .then(response => response.json())
    .then(data => {
        courses = data.course_types;

        var selectElement = document.querySelector('select[name="courses"]');
        courses.forEach(function (course) {
            var option = document.createElement('option');
            option.value = course;
            option.text = course;
            selectElement.appendChild(option);
        });
    })
    .catch(error => console.error('Error fetching courses:', error));






function validateEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}


function validateAndSend() {
  

    const title = document.querySelector('.title').value;
    const name = document.querySelector('.name').value;
    const email = document.querySelector('.email').value;
    const areaCode = document.querySelector('#areaCodesSelect').value;
    const phone = document.querySelector('.phone').value;
    const course = document.querySelector('[name="courses"]').value;
    const city = document.querySelector('[name="cities"]').value;
    const locatedWay = document.querySelector('[name="located-ways"]').value;
    const hours = document.querySelector('[name="hours"]').value;
    const agreeTerm = document.getElementById('agree-term').checked;

    let formIsValid = true;

    if (title.trim() === '' && formIsValid) {
        formIsValid = false;
        alert('Title alanı boş bırakılamaz.');
        return;
    }

    if (name.trim() === '' && formIsValid) {
        formIsValid = false;
        alert('Name alanı boş bırakılamaz.');
        return;
    }

    if (email.trim() === '' && formIsValid) {
        formIsValid = false;
        alert('Email alanı boş bırakılamaz.');
        return;
    } else if (!validateEmail(email) && formIsValid) {
        formIsValid = false;
        alert('Geçersiz email adresi.');
        return;
    }

    if (areaCode.trim() === '' && formIsValid) {
        formIsValid = false;
        alert('Area Code alanı boş bırakılamaz.');
        return;
    }

    if (phone.trim() === '' && formIsValid) {
        formIsValid = false;
        alert('Phone alanı boş bırakılamaz.');
        return;
    }

    if (course.trim() === '' && formIsValid) {
        formIsValid = false;
        alert('Course alanı boş bırakılamaz.');
        return;
    }

    if (city.trim() === '' && formIsValid) {
        formIsValid = false;
        alert('City alanı boş bırakılamaz.');
        return;
    }

    if (locatedWay.trim() === '' && formIsValid) {
        formIsValid = false;
        alert('Located Way alanı boş bırakılamaz.');
        return;
    }

    if (hours.trim() === '' && formIsValid) {
        formIsValid = false;
        alert('Hours alanı boş bırakılamaz.');
        return;
    }

    const agreeTermCheckbox = document.getElementById('agree-term');
    if (!agreeTermCheckbox.checked && formIsValid) {
        formIsValid = false;
        alert('Şartları kabul etmelisiniz.');
        return;
    }

    window.open("form_submitted_page.html", '_blank');
    return;

}

