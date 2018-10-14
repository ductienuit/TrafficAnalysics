import sys
sys.path.append('./utils')

from flask import Flask, request, make_response, abort, jsonify
from model import Model

app = Flask(__name__)
predict_model = Model()
VIDS_DIR = './vids'

#region error handler
@app.errorhandler(404)
def not_found(error):
    return make_response(jsonify({'error': 'Not found'}), 404)
#endregion

#region route

@app.route('/')
def root():
    return 'Hello world!'

@app.route('/traffic-data', methods=['GET'])
def get_data():
    time = request.args.get('time', str)
    location = request.args.get('location', str)

    
#endregion

if __name__ == "__main__":

    app.run(debug=True)