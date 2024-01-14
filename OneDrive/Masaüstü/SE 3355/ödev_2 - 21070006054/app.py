import sqlite3 as sql
from flask import Flask, redirect, render_template, request, url_for, g
import os

print(os.getcwd())

app = Flask(__name__)
app.config['DATABASE'] = './database.db'

def connect_db():
    if 'db' not in g:
        g.db = sql.connect(app.config['DATABASE'])
        g.db.row_factory = sql.Row
    return g.db


def initDB():
    database_path = app.config['DATABASE']


    if not os.path.exists(database_path):
        with sql.connect(database_path) as conn:
            conn.execute('CREATE TABLE IF NOT EXISTS products (product_no INTEGER PRIMARY KEY, name TEXT, description TEXT, price INTEGER, category TEXT, image TEXT)')
            print("Table created successfully")

            cursor = conn.cursor()
            cursor.execute("SELECT COUNT(*) FROM products")
            num_records = cursor.fetchone()[0]

        if num_records == 0:

            initial_data = [
                    (1, 'BYSPORTAKUS 100.yıl Sarı-kırmızı Isimsiz Forma ŞEN273333', 'USTA... ARMAYA BAK AKIL ARAMA', 309, 'Futbol Ürün', 'https://cdn.dsmcdn.com/mnresize/600/-/ty1072/product/media/images/prod/SPM/PIM/20231201/21/f0aadcfe-00ec-39c2-a7cc-1bfe37d95ba5/1_org_zoom.jpg'),
                    (2, 'Nevruz 100.yıl Sarı Lacivert Fener Forması JDE293QUQ-1', 'Dar ağacında olsak bile son sözümüz fener', 265, 'Futbol Ürün', 'https://cdn.dsmcdn.com/mnresize/600/-/mnresize/128/192/ty1077/product/media/images/prod/SPM/PIM/20231204/21/47375490-6eeb-397a-992f-5bc09e1160d3/1_org_zoom.jpg'),
                    (3, 'Enjoy Tavuklu Yetişkin Kedi Maması 15 Kg 395-0001', 'Kediciğinizin dişlerine layık :)', 680, 'Kedi Ürün', 'https://cdn.dsmcdn.com/mnresize/600/-/mnresize/128/192/ty438/product/media/images/20220524/16/115937481/486891448/1/1_org_zoom.jpg'),
                    (4, 'Petfor 20 LT Bebek Pudralı Kedi Kumu 8699956091440', 'Kediniz için pudralı kum', 112, 'Kedi Ürün', 'https://cdn.dsmcdn.com/mnresize/600/-/mnresize/128/192/ty1013/product/media/images/prod/SPM/PIM/20231006/22/427fcfc6-09e5-356d-8928-85b7ea7301ca/1_org_zoom.jpg'),
                    (5, 'TRENDYOL MAN Beyaz Erkek Oversize Basic Bisiklet Yaka Kısa Kollu %100 Pamuklu T-Shirt TMNSS22TS0318', 'Vücudu saran estetik tişört', 140, 'Erkek Tişört', 'https://cdn.dsmcdn.com/mnresize/600/-/mnresize/128/192/ty728/product/media/images/20230213/14/280205077/321501303/3/3_org_zoom.jpg'),
                    (6, 'TRENDYOL MAN Siyah Erkek Basic %100 Pamuklu Bisiklet Yaka Oversize Kısa Kollu T-Shirt TMNSS22TS0318', 'Basitlik ve sadeliğin zerafeti ile...', 130, 'Erkek Tişört', 'https://cdn.dsmcdn.com/mnresize/600/-/mnresize/128/192/ty968/product/media/images/20230718/10/394697226/915083908/2/2_org_zoom.jpg'),
                    (7, 'Gymhard Batman Premium Compression Uzun Kollu T-shirt 167600025', 'Spor salonlarının vazgeçilmez kıyafeti...', 315, 'Erkek Tişört', 'https://cdn.dsmcdn.com/mnresize/128/192/ty1077/product/media/images/prod/SPM/PIM/20231206/04/d3373c81-b655-3f10-bcee-69d6a5fbd564/1_org_zoom.jpg'),
                    (8, 'JAKARLI Unisex Yeşil Bisiklet Yaka Pamuk Oversize Boyfriend T-shirt JKMİS', 'JAKARLININ SON ESERİ', 50, 'Kadın Tişört', 'https://cdn.dsmcdn.com/mnresize/600/-/mnresize/128/192/ty1004/product/media/images/prod/SPM/PIM/20230920/10/ecb12e0f-abdc-3d8f-8f78-2873fb9837bd/1_org_zoom.jpg'),
                    (9, 'TRENDYOLMİLLA Beyaz-Siyah 2li Paket %100 Pamuk Basic Dik Yaka Örme T-Shirt TWOSS20TS1500', 'Yumuşacık pamuk pamuk tişört', 270, 'Kadın Tişört', 'https://cdn.dsmcdn.com/mnresize/600/-/mnresize/128/192/ty658/product/media/images/20221222/23/244488682/68282630/2/2_org_zoom.jpg'),
                    (10, 'Bricave Rahmetli De Sollardı Jdm Yazı Araba-motosiklet Cam Etiket Sticker 18x8cm Bri-076', 'Havanıza hava katacak sticker', 45, 'Oto Aksesuar', 'https://cdn.dsmcdn.com/mnresize/128/192/ty891/product/media/images/20230520/20/353689081/943119238/1/1_org_zoom.jpg'),
                    (11, 'Bricave Bakıyorum Ama Keyfime Yazı JDM Araba-Motosiklet Cam Etiket Sticker 28x12cm Bri-159', 'Havalı sözler, havalı insanlara yakışır :)', 55, 'Oto Aksesuar', 'https://cdn.dsmcdn.com/mnresize/128/192/ty984/product/media/images/20230812/15/402637098/989311828/1/1_org_zoom.jpg'),
                ]

            conn.executemany("INSERT INTO products (product_no, name, description, price, category, image) VALUES (?, ?, ?, ?, ?, ?)", initial_data)
            print("Initial data inserted successfully")

            rows = cursor.fetchall()
            print(rows)

        conn.commit()
        print("Database initialized successfully")



@app.route('/check_database')
def check_database():
    try:
        con = sql.connect("database.db")
        print(con)
        con.row_factory = sql.Row
        print(con.row_factory)

        cur = con.cursor()
        print(cur)
        cur.execute("SELECT * FROM products")

        rows = cur.fetchall()
        print(rows)
        con.close()

        return render_template("check_database.html", rows=rows)

    except Exception as e:
        return f"Error: {e}"


def get_search_results(search_query):
    try:
        con = sql.connect("database.db")
        con.row_factory = sql.Row

        cur = con.cursor()
        cur.execute("SELECT * FROM products WHERE name LIKE ?", ('%' + search_query + '%',))

        rows = cur.fetchall()

        return rows

    except Exception as e:
        print(f"Error in search operation: {str(e)}")
        return []
    finally:
        con.close()

@app.route('/search', methods=['POST'])
def search():
    search_query = request.form.get('search_query')

    search_results = get_search_results(search_query)

    return render_template('searchResults.html', search_results=search_results)

@app.route('/detail')
def detail():
   return render_template('detailPage.html')


@app.route('/')
def home():
    try:
        with connect_db() as con:
            cur = con.cursor()
            cur.execute("SELECT * FROM sqlite_master WHERE type='table' AND name='products'")
            table_exists = cur.fetchone()

            if not table_exists:
                initDB()

            cur.execute("SELECT * FROM products")
            rows = cur.fetchall()

        return render_template("home.html", rows=rows)
    except Exception as e:
        return f"Error: {e}"


@app.route('/addrec', methods=['POST', 'GET'])
def addrec():
    if request.method == 'POST':
        try:
            product_no = request.form['product_no']
            name = request.form['name']
            description = request.form['description']
            price = request.form['price']
            category = request.form['category']
            image = request.form['image']

            with sql.connect("database.db") as con:
                cur = con.cursor()
                cur.execute(
                    "INSERT INTO products (product_no, name, description, price, category, image) VALUES (?, ?, ?, ?, ?, ?)",
                    (product_no, name, description, price, category, image)
                )

                con.commit()
                msg = "Record successfully added"
        except Exception as e:
            con.rollback()
            msg = f"Error in insert operation: {str(e)}"
        finally:
            con.close()
            return render_template("check_database.html", msg=msg)

def fetch_columns(cursor):
    return [column[0] for column in cursor.description]

@app.route('/getrec/<id>', methods=['GET'])
def getrec(id):
    try:
        with sql.connect("database.db") as con:
            cur = con.cursor()
            print(id)
            cur.execute("SELECT * FROM products WHERE product_no = ?", (id,))
            row = cur.fetchone()
            print(row)
            if row:
                 # Get column names and create a dictionary dynamically
                columns = fetch_columns(cur)
                row_dict = {columns[i]: row[i] for i in range(len(columns))}

                return render_template("detailPage.html", row=row_dict)
            else:
                return render_template("detailPage.html", row=None, msg="No data found.")
    except Exception as e:
        return render_template("detailPage.html", row=None, msg=f"Error in retrieving data: {str(e)}")


@app.route('/initialize_database')
def initialize_database():
    initDB()
    return "Database initialized successfully"


if __name__ == '__main__':
    initDB()
    app.run(port=60051)
