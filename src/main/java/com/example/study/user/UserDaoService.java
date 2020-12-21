package com.example.study.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 3;

    static{
        users.add(new User(1, "Lena", new Date(), "pass1", "701010-1111111"));
        users.add(new User(2, "Hi", new Date(), "pass2", "801010-2222222" ));
        users.add(new User(3, "Okky", new Date(), "pass3", "901010-1111111" ));

    }


    public List<User> findAll(){
        return users;
    }


    public User save(User user){
        if(user.getId() == null){
            user.setId(++usersCount);
        }
        users.add(user);

        return user;
    }


    public User findOne(int id){

        for (User user: users){
            if(user.getId() == id){
                return user;
            }
        }

        return null;
    }


    public User deleteById(int id){
        Iterator<User> iterator = users.iterator(); //이터레이터 = list형태의 데이터 값을 순차적으로 접근해서 사용하기위한 열거형 데이터타입

        while(iterator.hasNext()){
            User user = iterator.next(); //user안에 포함되어있는 데이터가 순차적으로 열거됨

            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }

        return null;

    }


    public void updateUser(User user){

        int index = users.indexOf(user);

        users.set(index, user);

    }


}
