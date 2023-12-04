package com.example.blog.test;

import java.util.function.Supplier;

import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.RoleType;
import com.example.blog.model.User;
import com.example.blog.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.*;

// html파일이 아니라 data를 리턴해주는 controller
@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    // save함수는 id를 전달하지 않으면 insert를 해주고
    // save함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
    // save함수는 id를 전달하면 해당 iddp 대한 데이터가 없으면 insert를 한다.

    // @DeleteMapping("/dummy/user/{id}")
    // public String delete(@PathVariable int id) {
    // try {
    // userRepository.deleteById(id);
    // } catch (EmptyResultDataAccessException e) {
    // return "삭제에 실패하였다 해당 id는 DB에 없다";
    // }
    // return "삭제되었씁니다.id : " + id;
    // }
    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        if (!userRepository.existsById(id)) {
            return "삭제에 실패했습니다. 해당 id는 DB에 존재하지 않습니다.";
        }
        userRepository.deleteById(id);
        return "삭제되었습니다. id: " + id;
    }

    @Transactional
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json데이터를 요청=>Java
                                                                                  // Object(MessageConverter의
                                                                                  // Jackson라이브러리가 변환해서 받아줘요)
        System.out.println("id : " + id);
        System.out.println("password : " + requestUser.getPassword());
        System.out.println("id : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패");
        });
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // userRepository.save(user);

        // 더티 체킹 update시 save를 사용하지 않아도 된다 @Transactional
        return user;

    }

    // localhost/dummy/user
    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    // 한페이지당 2건에 데이터를 리턴받아 볼 예정
    @GetMapping("/dummy/user")
    public List<User> pageList(
            @PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} 주소로 파마레터를 전달 받을 수있다
    // localhost/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user4를 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이될것아냐>??
        // 그럼 return null 이 리턴이 되잖아 그럼 프로그램 문제 가 될거같아
        // Optinal로 너의 User객체를 감싸서 갸져올테니 null인지 아닌지 판단해서 return해

        // 람다식
        // User user = userRepository.findById(id).orElseThrow(() -> {
        // return new IllegalArgumentException("해당 유저는 없습니다.id:" + id);
        // });
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 없습니다.id:" + id);
            }
        }); // 에러가 뜨는대신에 콘솔값처럼 없다고 알려줌

        // orElseGet(new Supplier<User>(){
        // @Override
        // public User get() {
        // return new User();
        // }
        // }); //빈칸은 다 널로 나온다

        // 요청: 웹브라우저
        // user 객체 = 자바 오브젝트
        // 변환 ( 웹브라우저가 이해할 수 있는 데이터 )->json(Gson 라이브러리)
        // 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
        // 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져준다.
        return user;
    }

    // http의 boy에 username,password,email데이터 가지고 (요청)
    @PostMapping("/dummy/join")
    public String join(User user) {// key value 형태로 받는다.
        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입이 완료되었습니다.";

    }
}
