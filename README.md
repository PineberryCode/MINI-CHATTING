# MINI-CHATTING
### Summary
This application has a login and an account registration elaborated in **fxml**. The passwords are encrypted with RSA, also you have possibility of add friends.
### Preview

[![Mini Chat video - Overview](https://img.youtube.com/vi/0oSjsvoU0fQ/0.jpg)](https://youtu.be/0oSjsvoU0fQ)

- - -
### Configure
Create the **config** dir into the **database** dir. Into the **config** make and set up an interface called **Parameters** and paste the connection line that provides [MongoDB Cloud](https://account.mongodb.com/account/login):
```JAVA
"mongodb+srv://<username>:<password>@cluster0.cvb3g0s.mongodb.net/?retryWrites=true&w=majority;"
```
You can find the string connection in the MongoDB section **"Connect to cluster"** in **"Drivers"** then select **"JAVA"**:

To remember You should clone the repo [quarkus-api-rsa](https://github.com/PineberryCode/quarkus-api-rsa) for utilize the RSA that provides.<br>
###### Note_01: Run first quarkus-api-rsa before MINI-CHATTING.
###### Note_02: For communication between users, You shall turn on the server first.
- - -
Note: This application has things to improve, such as a user can communicate with another **specific** user because it looks like a **chat room**.
