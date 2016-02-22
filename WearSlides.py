from sys import platform as _platform
import os
#WearSlides server software
#By Konrad Iturbe and Parkerlreed
#Make sure Flask and PyUserInput are installed, refer to http://github.com/KonradIT/WearSlides for more information.

methodKey = ""
if _platform == "linux" or _platform == "linux2":
    methodKey = "0"
elif _platform == "win32":
    methodKey = "1"

##
if methodKey == "0":
    from flask import Flask
    from pykeyboard import PyKeyboard
    print("Linux OS")
    os.system("echo IP Address: ; ip route get 8.8.8.8 | awk '{print $NF; exit}'")
    k = PyKeyboard()
    try:
        import flask
    except ImportError:
        print("flask IS NOT INSTALLED")
        print("Please run: pip install flask")
        exit()
    try:
        import pykeyboard
    except ImportError:
        print("PyUserInput IS NOT INSTALLED")
        print("Please run: pip install PyUserInput")
        exit()

    app = Flask(__name__)

    @app.route('/')
    def index():
        return 'WearSlides server running!'

    @app.route('/next')
    def nextkey():
        k.tap_key(k.right_key)
        return 'OK'

    @app.route('/prev')
    def prevkey():
        k.tap_key(k.left_key)
        return 'OK'

    if __name__ == '__main__':
        app.run(debug=True, host='0.0.0.0')

elif methodKey == "1":
    from flask import Flask
    import ctypes
    print("Windows OS")
    os.system("ipconfig | findstr /R /C:\"IPv4 Address\"")
    try:
        import flask
    except ImportError:
        print("FLASK IS NOT INSTALLED")
        exit()
    try:
        import ctypes
    except ImportError:
        print("CTYPES IS NOT INSTALLED")
        exit()
    app = Flask(__name__)

    @app.route('/')
    def index():
        return 'WearSlides server running!'

    @app.route('/next')
    def nextkey():
        ctypes.windll.user32.keybd_event(0x27, 0, 0, 0)
        ctypes.windll.user32.keybd_event(0x27, 0, 0x0002, 0)
        return 'OK'

    @app.route('/prev')
    def prevkey():
        ctypes.windll.user32.keybd_event(0x25, 0, 0, 0)
        ctypes.windll.user32.keybd_event(0x25, 0, 0x0002, 0)
        return 'OK'

    if __name__ == '__main__':
        app.run(debug=True, host='0.0.0.0')
