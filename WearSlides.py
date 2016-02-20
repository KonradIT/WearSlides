import ctypes
import time
from flask import Flask

#WearSlides server software
#By Konrad Iturbe 2016
#Make sure Flask is installed, refer to http://github.com/KonradIT/WearSlides for more information.

app = Flask(__name__)

@app.route('/')
def index():
    return 'WearSlides server running!'

@app.route('/next')
def nextkey():
    ctypes.windll.user32.keybd_event(0x27, 0, 0, 0) #A is down
    ctypes.windll.user32.keybd_event(0x27, 0, 0x0002, 0) #A is up
    print("Right key")
    return 'OK'

@app.route('/prev')
def prevkey():
    ctypes.windll.user32.keybd_event(0x25, 0, 0, 0) #A is down
    ctypes.windll.user32.keybd_event(0x25, 0, 0x0002, 0) #A is up
    print("Left key")
    return 'OK'

if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')

