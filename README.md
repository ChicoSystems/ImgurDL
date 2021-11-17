ImgurDL
=======
Jar download: http://sourceforge.net/projects/imgurdl/

Loadur (previously ImgurDL) is a [Java Swing](http://en.wikipedia.org/wiki/Swing_%28Java%29) application that allows you to easily download galleries and albums from [Imgur](http://imgur.com). Loadur, originally known as [ImgurDL](http://lmgtfy.com/?q=ImgurDL) has been downloaded and used by tens of thousands of people from all over the globe.

<p align="center" alt="A mockup, not a screenshot">
  <img src="http://i.imgur.com/YR9BdZs.png">
</p>

Loadur was originally created in 2010 so I could easily download galleries and albums from Imgur. This was before Imgur made a public API available so instead of handling data from imgur in an easy format like [JSON](http://hg.pidgin.im/pidgin/main). Instead of doing something so simple I had to write an [HTML](http://www.w3schools.com/html/html_intro.asp) spider to allow me to extract images from an Imgur gallery.

We are currently migrating the backend away from using the old web spider and towards using the "Official" [Imgur API](https://api.imgur.com/). This should make Loadur easier to use and make the Imgur folks happy that we aren't slamming their servers like we used to in the good ol' days...

Loadur is now [Public Domain](http://en.wikipedia.org/wiki/Public_domain) software. This means you can do pretty much anything (only legal things) that you want to with it. You can modify it, rename it, repackage it and start your own app development company with it. You could always check out the code, make improvements and then [send](https://help.github.com/articles/using-pull-requests/) those improvements back to me. Then anyone that uses it later will be using your improvements! The only thing you can't do with this software is steal it, because I'm giving it to you. [Here](https://help.github.com/articles/fork-a-repo/) you can have this. If you wanted to leave my name in the source I wouldn't mind. Some future employer might see it and want to hire me because of my stunning [genius](http://en.wikipedia.org/wiki/Dunning%E2%80%93Kruger_effect). 

I could have left Loadur [proprietary](http://www.gnu.org/proprietary/proprietary.en.html) and attempted to make more money off of it, but I think that making it Open Source and releasing it into the public domain will cause this software to be of more use to, and more effective to the people who use and develop it.

### Getting started

To Use the app on Windows, Linux or Mac you can download the jar here:

Jar download: http://sourceforge.net/projects/imgurdl/

## Contributing to Loadur

It's easy to contribute to loadur.

You need a few things to be able to contribute to loadur:

1. Git
2. Java
3. Eclipse

If you already have these programs ready to use you can skip the next section.

### Installing Development Tools (WINDOWS)
Whether you have Linux or Windows it's pretty easy to install the tools you need to contribute to Loadur.

#### Git (Windows)
Installing Git for windows is as easy as installing any other windows application, download it here:
[Git](https://git-scm.com/download/win)
To Check if Git is properly installed right click on any file in a Windows folder and you'll see Git options in the menu:
<p align="center" alt="Git Right Click">
  <img src="http://i.imgur.com/TjOiu4c.png">
</p>
If this does not work you can try using the Github for Windows app, it's not as good as the pure Windows Git app though:
[Github App](http://i.imgur.com/TjOiu4c.png)

#### Java (Windows)
Java is also easy to download and run for Windows, get it here:
[Java](https://java.com/en/download/)
To verify that Java is loaded, open a Command Prompt and type:
```bash
C:\Users\Isaac\Desktop> java -version
java version "1.8.0_40"
Java(TM) SE Runtime Environment (build 1.8.0_40-b26)
Java HotSpot(TM) Client VM (build 25.40-b25, mixed mode, sharing)
```
If Java does not respond to this command it means you'll need to [add it to your PATH](http://www.kingluddite.com/tools/how-do-i-add-java-to-my-windows-path)

#### Eclipse (Linux / Windows / Macintosh)
You can download Eclipse for your preferred operating system here:
[Eclipse Downloads](https://eclipse.org/downloads/packages/eclipse-ide-java-developers/lunasr2)
Pick your OS, download and install.

### Installing Development Tools (Linux)
Some of these tools are installed slightly differently (easier) when you are using a real OS.

#### Git (Linux)
You're using Linux. You probably already know how to install git. But if you're new, just do this:
##### Debian Flavored
```bash
sudo apt-get install git
```

##### Red-Hat Flavored
```bash
yum install git
```

To verify you now have git installed run:
```bash
isaac@ubuntu-desktop:~$ git --version
git version 1.9.1
```
If you have any other distribution you, again, probably already know how to install git, if it's not installed already.

#### Java (Linux)
Java is also very simple to download and run for Linux, run the following:
```bash
sudo apt-get install java
```
or
```bash
sudo yum install java
```
To verify that Java is loaded, open a Terminal and type:
```bash
java -version
```
You should get a response similar to this:
```java version "1.7.0_79"```
If Java does not respond to this command it means you'll need to [add it to your PATH](https://java.com/en/download/help/path.xml)

#### Eclipse (Linux / Windows / Macintosh)
You can download Eclipse for your preferred operating system here:
[Eclipse Downloads](https://eclipse.org/downloads/packages/eclipse-ide-java-developers/lunasr2)
Pick your OS, download and install.

## Running Loadur
1. Clone the Git repository into the folder you would like to work in:
```bash
git clone https://github.com/ChicoSystems/ImgurDL.git
```
2. Import the project you just cloned into Eclipse:
```bash
File -> Import -> Git -> Projects From Git -> local -> Add -> Browse
```
3. Run The Main Loadur Class in Eclipse
```bash
Right Click Project Folder -> Run As -> Java Application
```
Loadur should now be running, go ahead and press the download button using the default URL and download some nice wallpapers. Or perhaps you'd like to put in an Imgur gallery or album URL? Try it. If something breaks you can check the code and fix it. Then send me a pull request, or a patch, as detailed in the next section.


Clone repository and import the project into Eclipse.

## Change Log

#### Loadur v0.3
+ Updated backend url from chicosystems.com:3000 to api.chicosystems.com:3000
+ Changed library for http connection to something included in jdk 11+

#### Loadur v0.2.5
+ Added Menu
+ Added Directory Chooser - User Now can choose another directory to download to.
+ Moved Update Alert into the Menu.
+ Fixed Menu Disappearing Bug.
+ Fixed Picture Downloading with wrong colors bug.
+ + Java's fault, we had to use the TwelveMonkeys ImageIO plugins to fix it.

#### Loadur v0.2
+ Made textField Auto Focus
+ Enter Button now initiates search.
+ Hitting the button now highlights text letting user type over it.
+ Put API calls in their own thread to reduce latency.
+ Added a taskbar icon, tested working with linux and windows.
+ Fixed background size issue.
+ Put version info in app title.
+ Updated readme with this changelog.

#### Loadur v0.1.2
+ Added more info to report home.
+ Added user notification when update is available.
+ Made it so albums could be downloaded directly from link again.

#### Loadur v0.1
+ Made Download button turn into stop button.
+ Added ReportHome api to track app usage.
+ Changed from using direct links to search feature.

#### Loadur v0.0.5
+ Resized display area.
+ Update Readme with lots of info
+ Added Licence to Project
+ Reworked Stats Tracker
+ Changed from web spider to Imgur api client


#### Loadur v0.0.4
+ Imgur changed their html again. Updated some spider delimiters.
+ Changed the logo URL to something that can be monitored.
+ Moved stats to the top of app.

#### Loadur v0.0.3
+ Program Works again.
+ Fixed bug where pictures download multiple time.
+ Now Pictures already in directory don't get downloaded again.
+ Based on pictures name.extention.
+ Added Stats system, Displaying Kbits, Queue, Downloaded, and Folder
+ Updated input system. It's a bit more robust now.

#### Loadur v0.0.2
+ Update default download page
+ Added file checking to make sure duplicates don't get saved.
+ Added stats system monitoring.


#### Loadur v0.0.1
+ Just a Basic App Designed to Download Imgur Galleries
+ Created Frontend to show user pics as they are downloading.
