# encoding: utf-8
import json
import random
from flask import Flask, request, jsonify, g
import sqlite3
app = Flask(__name__)

DATABASE = 'Communication_API/database.db'

def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE)
    return db

@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()

@app.route("/", methods=["GET", "POST"])
def starting_url():
    conn = get_db()
    cur = conn.cursor()
    answer = {"answer":[]}
    query = None
    idList = []
    try:
        match request.method:
            case 'GET':

                query = cur.execute(f'SELECT Id, Temperature FROM Temperature')

                for row in query:
                    temp = round(row[1] + random.uniform(1.0, 50.0),2)
                    answer['answer'].append(
                        {
                            "id":row[0],
                            "temperature":temp
                        }
                    )

            case 'POST':
                ask = dict(json.loads(request.data))
                for item in ask['ask']:
                    if item["id"] == None:
                        cur.execute(f'INSERT INTO Temperature (Temperature) VALUES ({item["temperature"]})')
                        conn.commit()
                        lastIdQuery = cur.execute('SELECT last_insert_rowid() FROM Temperature LIMIT 1')
                        item["id"] = lastIdQuery.fetchone()[0]

                    else:
                        cur.execute(f'UPDATE Temperature SET Temperature={item["temperature"]} WHERE Id = {item["id"]}')
                        conn.commit()

                    idList.append(item["id"])

                idList = str(idList).replace('[','(').replace(']',')')

                query = cur.execute(f'SELECT Id, Temperature FROM Temperature WHERE Id IN {idList}')

                for row in query:
                    answer['answer'].append(
                    {
                        "id":row[0],
                        "temperature":row[1]
                    }
                )
    finally:
        cur.close()
        conn.close()
    
    return jsonify(answer)


app.run(host="0.0.0.0", debug=True)