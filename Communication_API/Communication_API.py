# encoding: utf-8
import json
from flask import Flask, request, jsonify

app = Flask(__name__)

@app.route("/", methods=["GET", "POST"])
def starting_url():
    ask = dict(json.loads(request.data))
    answer = None

    match request.method:
        case 'GET':
            answer = {"answer":[]}

            temperature = None
            
            for item in ask['ask']:
                print(item['id'])
                temperature = item['id']
                answer['answer'].append(
                    {
                        "id":item['id'],
                        "temperature":temperature
                    }
                )
        case 'POST':
            for item in ask['ask']:
                print(f'id: {item["id"]}')
                print(f'temperature: {item["temperature"]}')
                print('------------------------')

    return jsonify(answer)


app.run(host="0.0.0.0", debug=True)