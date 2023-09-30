package board.blog.controller;

public class MemberForm {
    private String name;   // 회원 이름
    private String userId;  // 회원 id
    private String userPw;  // 회원 비밀번호
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }



}
