##WearSlides

Control your presentation from your Android Wear watch.

###Features:

* Minimal controls, only Previous slide and Next slide
* Works with most presentation softwares (MS Office/LibreOffice/OpenOffice/Prezi...)
* Only requires Python, PIP and Flask to work (portable .exe coming soon)
* and probably more...

###To do:
* Offline mode for Android Wear watch (in case you dont have smartphone right there)
* Portable .exe webserver
* ~~Add timer~~

###Downloads:

#####Smartphone app (syncs to Watch):

* Releases: http://github.com/KonradIT/WearSlides/releases
* Google Play: https://play.google.com/store/apps/details?id=com.chernowii.wearslides

#####Computer script:

[WearSlides.py](WearSlides.py)

NOTE: The computer needs to have Python installed, as well as PIP and Flask.

Depending on the OS, it needs to have additional modules installed that control the keys:

* Windows: ctypes
* Linux: PyUserInput (Which needs Xlib)

To install them: [Download Python](http://python.org) , [Download and Install PIP](https://pip.pypa.io/en/latest/installing/#installing-with-get-pip-py) , Once Python and PIP are installed, type in command line: `pip install flask` (run as Admin/Sudo)
 
PyUserInput: `pip install PyUserInput`

Xlib: `sudo apt-get install python-xlib`
A portable .exe file (for carrying in Pendrives) is coming soon

###Video

How to install/configure/etc... https://youtu.be/o8M0rRYRMt8