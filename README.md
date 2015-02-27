# unofficial-cbox-android-sdk
[Unofficial] Cbox Android Sdk

This android project was created to allow the native interaction with a Cbox Chat (http://www.cbox.ws/) from an Android application.

I used this project to allow the users of the mobile android application Moreirense1938 (https://play.google.com/store/apps/details?id=com.sandro.mfc) interact with the Cbox Chat avaliable at the blog Moreirense1938 (http://www.moreirense1938.com/).

The project have already implemented three actions:
- Login User
- Send a Message
- Remove a Message

How to use:

1. Import the project as an Android Library to your workspace
2. Get your CboxId and CboxTag (you can get this values from your cbox url: http://www5.cbox.ws/box/?boxid=CBOXID&boxtag=CBOXTAG&sec=form)
3. Instanciate the Cbox Object: CboxApi cbox = new CboxApi("CBOXID","CBOXTAG");
4. Login to get the user token:  String token = cbox.performALogin(USERNAME,PASSWORD);
5. Send a message: cbox.postAMessage(USERNAME, EMAIL, TOKEN, MESSAGE);


