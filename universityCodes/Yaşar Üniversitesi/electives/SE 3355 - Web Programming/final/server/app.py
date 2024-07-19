from flask import Flask, jsonify, request
from datetime import datetime
from flask_cors import CORS  # Import the CORS module
from werkzeug.security import generate_password_hash, check_password_hash
import sqlite3

app = Flask(__name__)
CORS(app)  # Enable CORS for all routes
print(app)

def createHotelsTable():
    # Connect to the database (this will create a new file if it doesn't exist)
    conn = sqlite3.connect('hotels.db')

    # Create a cursor object to execute SQL statements
    cursor = conn.cursor()

    # Create a table for hotels
    cursor.execute('''
        CREATE TABLE IF NOT EXISTS hotels (
            id INTEGER PRIMARY KEY,
            imageLink TEXT,
            rating REAL,
            hotelName TEXT,
            hotelDesc TEXT,
            location TEXT,
            country TEXT,
            price INTEGER,
            stayDetails TEXT,
            locationLink TEXT,
            locationInfo TEXT,
            allAmenities TEXT,  -- Assuming you want to store amenities as a JSON or comma-separated list
            commentAmount INTEGER,
            availableFrom DATE,
            availableTo DATE,
            isCheapToMembers BOOLEAN, 
            availableRoomAmount int
        )
    ''')

    # Insert a sample record
    cursor.execute('''
        INSERT INTO hotels (
            id, imageLink, rating, hotelName, hotelDesc, location, country, price, stayDetails,
            locationLink, locationInfo, allAmenities, commentAmount, availableFrom, availableTo, isCheapToMembers, availableRoomAmount
        ) VALUES (
            1, 'https://images.trvl-media.com/lodging/2000000/1210000/1207100/1207007/62555c3d_w.jpg?impolicy=fcrop&w=600&h=400&p=1&q=high',
            9.6, 'Lara Barut Collection - Ultra All Inclusive', 'Lara Plajı yakınında, kongre merkezi bağlantılı, tam donanımlı spa olan her şey dâhil resort otel', 'Antalya', 'Turkey',
            7739,
            '2 gecelik için', 
            'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d25538.871677594318!2d30.83790233519331!3d36.85782586928015!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14c359420fff7e37%3A0x3bf6a3a890bbd8f2!2sLara%20Barut%20Collection!5e0!3m2!1str!2str!4v1704991199346!5m2!1str!2str',
            'Güzeloba Mah. Yaşar Sobutay Mah. No.: 30, Lara, Antalya, Antalya, 07235',
            '["Restoran", "Bar", "Havuz", "Spa", "Spor Salonu", "Ücretsiz kablosuz internet"]',
            650,
            '2024-01-18',
            '2024-01-23',
            'true', 2
        )
    ''')

    # Insert the 2nd record
    cursor.execute('''
        INSERT INTO hotels (
            id, imageLink, rating, hotelName, hotelDesc, location, country, price, stayDetails,
            locationLink, locationInfo, allAmenities, commentAmount, availableFrom, availableTo, isCheapToMembers, availableRoomAmount
        ) VALUES (
            2, 'https://images.trvl-media.com/lodging/9000000/8300000/8291700/8291672/9cefea27_w.jpg?impolicy=fcrop&w=600&h=400&p=1&q=high',
            9.4, 'Casa Nonna Bodrum - Adult Only', 'Bodrum bölgesinde, plaja sıfır, spa, ücretsiz plaj servisi olan otel.', 'Bodrum', 'Turkey',
            7817,
            '2 gecelik için', 
            'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3186.247034840965!2d27.459294811388233!3d37.00390255607451!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14be6b7be1e659c9%3A0x1bc22ebad3fa2859!2sCasa%20Nonna%20Bodrum!5e0!3m2!1str!2str!4v1704995890363!5m2!1str!2str',
            'İçmeler Mevkii, P.K. 357, Bodrum, Muğla, 48400',
            '["Restoran", "Bar", "Havuz", "Ücretsiz kablosuz internet", "Spor Salonu", "Klima"]',
            48,
            '2024-01-22',
            '2024-01-27',
            'false', 3
        )
    ''')

    # Insert the 3rd record
    cursor.execute('''
        INSERT INTO hotels (
            id, imageLink, rating, hotelName, hotelDesc, location, country, price, stayDetails,
            locationLink, locationInfo, allAmenities, commentAmount, availableFrom, availableTo, isCheapToMembers, availableRoomAmount
        ) VALUES (
            3, 'https://images.trvl-media.com/lodging/68000000/67420000/67416300/67416227/e1f70e7d_w.jpg?impolicy=fcrop&w=600&h=400&p=1&q=high',
            7.8, 'Sundia Exclusive by Liberty Fethiye', 'Fethiye bölgesinde, plaja sıfır, spa, restoran olan otel.', 'İstanbul', 'Turkey', 
            4141,
            '2 gecelik için', 
            'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d25538.871677594318!2d30.83790233519331!3d36.85782586928015!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14c359420fff7e37%3A0x3bf6a3a890bbd8f2!2sLara%20Barut%20Collection!5e0!3m2!1str!2str!4v1704991199346!5m2!1str!2str',
            'Çalis Beach, Foca Mah.1085. Sok., Fethiye, Mugla, 48300',
            '["Restoran", "Bar", "Havuz", "Ücretsiz kablosuz internet", "Klima", "Spor Salonu"]',
            10,
            '2024-01-26',
            '2024-01-31',
            'true', 1
        )
    ''')

    # Insert the 3rd record
    cursor.execute('''
        INSERT INTO hotels (
            id, imageLink, rating, hotelName, hotelDesc, location, country, price, stayDetails,
            locationLink, locationInfo, allAmenities, commentAmount, availableFrom, availableTo, isCheapToMembers, availableRoomAmount
        ) VALUES (
            4, 'https://images.trvl-media.com/lodging/1000000/30000/22300/22287/33fa163a.jpg?impolicy=resizecrop&rw=598&ra=fit',
            7.2, 'The Dixie Hollywood', 'Hotel with outdoor pool, near Hollywood Boulevard', 'Los Angeles', 'USA',
            6000,
            'For 1 night', 
            'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3303.7463511926026!2d-118.3102823887271!3d34.10163781500849!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x80c2bf50f3eaa277%3A0xcd2d8d1f7b02fa58!2sThe%20Dixie%20Hollywood%20Hotel!5e0!3m2!1str!2str!4v1705141705306!5m2!1str!2str',
            '5410 Hollywood Blvd, Los Angeles, CA, 90027',
            '["Havuz", "Havaalanı transferi", "Bar", "Ücretsiz kablosuz internet", "Çamaşırhane","Klima", "7/24 açık resepsiyon"]',
            1023,
            '2024-01-18',
            '2024-01-23',
            'true', 2
        )
    ''')

    # Insert the 3rd record
    cursor.execute('''
        INSERT INTO hotels (
            id, imageLink, rating, hotelName, hotelDesc, location, country, price, stayDetails,
            locationLink, locationInfo, allAmenities, commentAmount, availableFrom, availableTo, isCheapToMembers, availableRoomAmount
        ) VALUES (
            5, 'https://images.trvl-media.com/lodging/1000000/20000/19800/19768/ea54de87.jpg?impolicy=resizecrop&rw=598&ra=fit',
            8.0, 'New York Hilton Midtown', 'Upscale hotel with 2 restaurants, near 5th Avenue', 'New York', 'USA',
            7231,
            'For 1 nights', 
            'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3021.9896608589006!2d-73.98177028844894!3d40.76225213441839!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x89c25855c0679529%3A0x2a34371cb33f3c80!2sNew%20York%20Hilton%20Midtown!5e0!3m2!1str!2str!4v1705142208837!5m2!1str!2str',
            '1335 Avenue Of The Americas, New York, NY, 10019',
            '["Spa", "Ücretsiz kablosuz internet", "Spor Salonu", "Klima", "Restoran", "Bar"]',
            1467,
            '2024-01-22',
            '2024-01-27',
            'true', 3
        )
    ''')

    # Insert the 3rd record
    cursor.execute('''
        INSERT INTO hotels (
            id, imageLink, rating, hotelName, hotelDesc, location, country, price, stayDetails,
            locationLink, locationInfo, allAmenities, commentAmount, availableFrom, availableTo, isCheapToMembers, availableRoomAmount
        ) VALUES (
            6, 'https://images.trvl-media.com/lodging/69000000/68110000/68102900/68102878/e1245d5b.jpg?impolicy=resizecrop&rw=598&ra=fit',
            9.4, 'Canopy by Hilton Chicago Central Loop', 'Upscale hotel with restaurant, near Willis Tower', 'Chicago', 'USA', 
            14786,
            'For 1 nights', 
            'https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2970.680956358433!2d-87.63685138839777!3d41.87821026528745!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x880e2de6ad83a821%3A0x3a5149b42db40fcb!2sCanopy%20by%20Hilton%20Chicago%20Central%20Loop!5e0!3m2!1str!2str!4v1705142562501!5m2!1str!2str',
            '226 West Jackson Blvd, Chicago, IL, 60606',
            '[  "Ücretsiz kablosuz internet", "Spor Salonu", "Restoran", "Bar", "Klima", "7/24 açık resepsiyon"]',
            10,
            '2024-01-26',
            '2024-01-31',
            'true', 1
        )
    ''')


    # Commit the changes and close the connection
    conn.commit()
    conn.close()

def create_users_table():
    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()

    # Create a table for users
    cursor.execute('''
        CREATE TABLE Users (
            id INTEGER PRIMARY KEY,
            Name TEXT NOT NULL,
            Surname TEXT NOT NULL,
            email TEXT UNIQUE NOT NULL,
            password TEXT NOT NULL,
            country TEXT,
            city TEXT
        )
    ''')

    # Insert sample records with hashed passwords
    cursor.execute('''
        INSERT INTO Users (Name, Surname, email, password, country, city)
        VALUES ('Ahmet', 'Yılmaz', 'ahmet@example.com', ?, 'Turkey', 'Istanbul')
    ''', (generate_password_hash('hashed_password_tr1'),))

    cursor.execute('''
        INSERT INTO Users (Name, Surname, email, password, country, city)
        VALUES ('John', 'Doe', 'john@example.com', ?, 'USA', 'New York')
    ''', (generate_password_hash('hashed_password_usa2'),))

    conn.commit()
    conn.close()

@app.route('/login', methods=['POST'])
def login():
    data = request.json
    email = data.get('email')
    password = data.get('password')

    # Perform authentication logic here (e.g., check credentials against the database)

    # For example, assuming you have a function check_credentials(email, password)
    user_info = check_credentials(email, password)
    if user_info:
        name, surname, country = user_info
        return jsonify({'message': 'Login successful', 'name': name, 'surname': surname, 'locale': country})
    else:
        return jsonify({'message': 'Login failed'}), 401

def check_credentials(email, password):
    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()

    # Query to fetch user with the given email
    cursor.execute('''
        SELECT * FROM Users WHERE email = ?
    ''', (email,))
    user = cursor.fetchone()

    conn.close()

    if user:
        # Check if the provided password matches the hashed password in the database
        if check_password_hash(user[4], password):
            # Return name and surname on successful login
            return user[1], user[2], user[5]
    # User not found or incorrect password
    return None
# Backend - Flask
@app.route('/register', methods=['POST'])
def register():
    data = request.json
    name = data.get('name')
    surname = data.get('surname')
    email = data.get('email')
    password = data.get('password')
    country = data.get('country')
    city = data.get('city')

    # Perform user registration logic here
    # (e.g., insert a new user into the database)

    # For example, assuming you have a function register_user
    if register_user(name, surname, email, password, country, city):
        return jsonify({'message': 'Registration successful'})
    else:
        return jsonify({'message': 'Registration failed'}), 400

def register_user(name, surname, email, password, country, city):
    # Implement user registration logic
    # Insert the new user into the database
    conn = sqlite3.connect('users.db')
    cursor = conn.cursor()

    try:
        # Check if the email is already in use
        cursor.execute('SELECT * FROM Users WHERE email = ?', (email,))
        existing_user = cursor.fetchone()
        if existing_user:
            return False  # Registration failed, email already in use

        # Insert the new user
        cursor.execute('''
            INSERT INTO Users (Name, Surname, email, password, country, city)
            VALUES (?, ?, ?, ?, ?, ?)
        ''', (name, surname, email, generate_password_hash(password), country, city))

        # Commit the changes and close the connection
        conn.commit()
        return True  # Registration successful
    except Exception as e:
        print(f"Error during registration: {e}")
        return False  # Registration failed

    finally:
        # Close the connection
        conn.close()

@app.route('/', methods=['GET'])
def get_hotels():
    conn = sqlite3.connect('hotels.db')
    cursor = conn.cursor()

    # Get the country from the query parameters
    country = request.args.get('country', None)

    if country:
        # If country is provided, filter the results
        cursor.execute('SELECT * FROM hotels WHERE country = ?', (country,))
    else:
        # If country is not provided, get all results
        cursor.execute('SELECT * FROM hotels')

    columns = [col[0] for col in cursor.description]
    hotels = [dict(zip(columns, row)) for row in cursor.fetchall()]

    conn.close()
    return jsonify({'hotels': hotels})

@app.route('/hotel-detail/<id>', methods=['GET'])
def get_hotel_details(id):
    conn = sqlite3.connect('hotels.db')
    cursor = conn.cursor()

    # Use a parameterized query to avoid SQL injection
    cursor.execute('SELECT * FROM hotels WHERE id = ?', (id,))
    
    # Fetch all rows matching the query
    rows = cursor.fetchall()

    # Assuming 'id' is unique, get the first row
    if rows:
        columns = [col[0] for col in cursor.description]
        hotel = dict(zip(columns, rows[0]))
        conn.close()
        return jsonify({'hotels': [hotel]})
    else:
        conn.close()
        return jsonify({'hotels': []})




'''
@app.route('/search-results', methods=['GET'])
def get_filtered_hotels():
    search_text = request.args.get('search', '')
    available_room_amount = request.args.get('availableRoomAmount', None)
    entered_from = request.args.get('enteredFrom', '')
    entered_to = request.args.get('enteredTo', '')

    # Veritabanına bağlan
    conn = sqlite3.connect('hotels.db')
    cursor = conn.cursor()

    print(search_text)
    print(available_room_amount)
    print("a - " + entered_from)
    print("b - " + entered_to)

    try:
        entered_from = datetime.strptime(entered_from, '%Y.%m.%d').date()
        entered_to = datetime.strptime(entered_to, '%Y.%m.%d').date()

        print("ccc")
        print(entered_from)
        print(entered_to)   
        print("ccc")
    except ValueError:
        # Handle the case where entered_from or entered_to is not a valid date
        entered_from = ''
        entered_to = ''
    

    print("ddd")

    
    if search_text != '':
        if entered_from == '' or entered_to == '':
            cursor.execute("SELECT * FROM hotels WHERE location LIKE ? AND availableRoomAmount >= ?", ('%' + search_text + '%',available_room_amount ))
        elif entered_from != '' and entered_to != '':
            cursor.execute("SELECT * FROM hotels WHERE ((availableFrom <= ? AND availableTo >= ?) AND (availableFrom <= ? AND availableTo >= ?)) AND location LIKE ? AND availableRoomAmount >= ?",
                    (entered_from, entered_from, entered_to, entered_to, '%' + search_text + '%',available_room_amount))
    elif search_text == '':
        if entered_from == '' or entered_to == '':
            cursor.execute("SELECT * FROM hotels WHERE availableRoomAmount >= ?", (available_room_amount,))
        elif entered_from != '' and entered_to != '':
            cursor.execute("SELECT * FROM hotels WHERE ((availableFrom <= ? AND availableTo >= ?) AND (availableFrom <= ? AND availableTo >= ?)) AND availableRoomAmount >= ?",
                    (entered_from, entered_from, entered_to, entered_to, available_room_amount))



    # Sorguya uyan tüm satırları al
    rows = cursor.fetchall()

    print("hhh")


    # 'id' varsayılan olarak benzersiz olduğundan, ilk satırı al
    if rows:
        hotels = [dict(zip([col[0] for col in cursor.description], row)) for row in rows]
    else:
        hotels = []

    conn.close()
    return jsonify({'hotels': hotels})
'''
@app.route('/search-results', methods=['GET'])
def get_filtered_hotels():
    search_text = request.args.get('search', '')
    available_room_amount = request.args.get('availableRoomAmount', None)
    entered_from = request.args.get('enteredFrom', '')
    entered_to = request.args.get('enteredTo', '')

    # Get the decoded locale from the user session
    decoded_locale = request.args.get('locale')

    # Veritabanına bağlan
    conn = sqlite3.connect('hotels.db')
    cursor = conn.cursor()

    try:
        entered_from = datetime.strptime(entered_from, '%Y.%m.%d').date()
        entered_to = datetime.strptime(entered_to, '%Y.%m.%d').date()
    except ValueError:
        # Handle the case where entered_from or entered_to is not a valid date
        entered_from = None
        entered_to = None

    # Construct the base query
    base_query = "SELECT * FROM hotels WHERE"

    # Add conditions based on search_text
    if search_text:
        base_query += f" location LIKE '%{search_text}%' AND"

    # Add conditions based on entered_from and entered_to
    if entered_from and entered_to:
        base_query += f" ((availableFrom <= '{entered_from}' AND availableTo >= '{entered_from}') AND (availableFrom <= '{entered_to}' AND availableTo >= '{entered_to}')) AND"

    # Add conditions based on available_room_amount
    if available_room_amount:
        base_query += f" availableRoomAmount >= {available_room_amount} AND"

    # Add conditions based on decoded_locale (if available)
    if decoded_locale:
        base_query += f" country = '{decoded_locale}' AND"

    # Remove the trailing 'AND' if there are additional conditions
    if base_query.endswith("AND"):
        base_query = base_query[:-3]

    # Execute the final query
    cursor.execute(base_query)

    # Sorguya uyan tüm satırları al
    rows = cursor.fetchall()

    # 'id' varsayılan olarak benzersiz olduğundan, ilk satırı al
    if rows:
        hotels = [dict(zip([col[0] for col in cursor.description], row)) for row in rows]
    else:
        hotels = []

    conn.close()
    return jsonify({'hotels': hotels})
 


if __name__ == '__main__':
    print(app)
    app.run(debug=True)
    #createHotelsTable()