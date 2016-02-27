##WearSlides

Control your presentation from your Android Wear watch.

###Features:

* Minimal controls, only Previous slide and Next slide
* Works with most presentation softwares (MS Office/LibreOffice/OpenOffice/Prezi...)
* Timer for slide
* Standalone .exe program which does not need any dependency
* and probably more...

###To do:
* Offline mode for Android Wear watch (in case you dont have smartphone right there)
* ~~Portable .exe webserver~~
* ~~Add timer~~
* Linux portable program (bash shell?)
* Mac .app or .dmg portable program

###Downloads:

####Smartphone app (syncs to Watch):

* Releases: http://github.com/KonradIT/WearSlides/releases
* Google Play: https://play.google.com/store/apps/details?id=com.chernowii.wearslides

####Computer script:

#####The easy way:

If the computer runs Windows, just download the [exe program](wearslides-windows.exe) and execute it, a terminal will appear showing the IP address and other details.

![](mobile/src/main/res/drawable/terminal.png)

You can carry the .exe in a pendrive to carry around. No python/Pip/Flask needed.

Mac portable and Linux portable coming soon!

#####The hard way:

If you prefer to run the script in Python:

[WearSlides.py](WearSlides.py)

NOTE: The computer needs to have Python installed, as well as PIP and Flask.

Depending on the OS, it needs to have additional modules installed that control the keys:

* Windows: ctypes
* Linux: PyUserInput (Which needs Xlib)

To install them: [Download Python](http://python.org) , [Download and Install PIP](https://pip.pypa.io/en/latest/installing/#installing-with-get-pip-py) , Once Python and PIP are installed, type in command line: `pip install flask` (run as Admin/Sudo)
 
PyUserInput: `pip install PyUserInput`

Xlib: `sudo apt-get install python-xlib`

###Video

How to install/configure/etc... https://youtu.be/o8M0rRYRMt8

NOTE: The .exe has been compiled using PyInstaller.