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

## Running it

To compile, simply

Clone repository and import the project into Eclipse.
