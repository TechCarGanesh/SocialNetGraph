/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproj;

import java.util.Iterator;


class UserProfile {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private LList<String> friends;

    public UserProfile(String fName, String lName, String emailAddr, String phoneNum) {
        firstName = fName;
        lastName = lName;
        email = emailAddr;
        phone = phoneNum;
        friends = new LList<>();
    }

    public String getEmail() {
        return email;
    }
    
    public String getName() {
        return firstName + " " + lastName;
    }
    
    public String getPhone() {
        return phone;
    }

    public void addFriend(String friendEmail) {
        friends.add(friendEmail);
    }

    public void removeFriend(String friendEmail) {
        // friends.remove(friendEmail);
        int i = 1;
        for (String friend : getFriendsList()) {
            if (friend.equals(friendEmail)) {
                friends.remove(i);
                break;
            }
            i += 1;
        }
    }
    
    public String[] getFriendsList() {
        String[] friendsArray = new String[friends.getLength()];
        return friends.toArray();
    }

    public void modifyProfile(String fName, String lName, String emailAddr, String phoneNum) {
        if (firstName != null) firstName = fName;
        if (lastName != null) lastName = lName;
        if (email != null) email = emailAddr;
        if (phone != null) phone = phoneNum;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " (" + email + ", " + phone + ") - Friends: " + friends;
    }
    
    public LList getFriends() {
        return friends;
    }
    
}

