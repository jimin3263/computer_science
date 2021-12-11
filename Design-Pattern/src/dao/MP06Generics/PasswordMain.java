package dao.MP06Generics;


import java.util.List;

public class PasswordMain {
    public static void main(String[] args) {
        PasswordInfo p;
        DAO<PasswordInfo, String> passwordDAO = new PasswordDAOImpl("password");

        //DB 추가
        System.out.println("== DB 추가 ==");
        p = new PasswordInfo("https://www.smu.ac.kr", "smu", "abcde");

        System.out.println( passwordDAO.findByKey(p.getKey()));

        passwordDAO.insert(p);

        p = new PasswordInfo("https://www.smu2.ac.kr", "smu2", "abcde");
        passwordDAO.insert(p);

        //데이터가 제대로 추가되었는지 모든 데이터 출력
        System.out.println("== DB 확인 ==");
        List<PasswordInfo> all = passwordDAO.findAll();
        for (PasswordInfo passwordInfo : all) {
            System.out.println(passwordInfo);
        }

        //두 번째 데이터 id smu2 -> smu1
        System.out.println("== DB 수정 ==");
        PasswordInfo byKey = passwordDAO.findAll().get(1);
        //PasswordInfo byKey = passwordDAO.findByKey("https://www.smu2.ac.kr");
        byKey.setId("smu1");
        passwordDAO.update(byKey.getKey(),byKey);

        //데이터 제대로 수정되었는지 확인
        System.out.println("== DB 수정 확인 ==");
        PasswordInfo byKey1 = passwordDAO.findByKey("https://www.smu2.ac.kr");
        System.out.println(byKey1.getId());

        //smu2.ac.kr 삭제
        System.out.println("== DB 삭제 ==");
        passwordDAO.delete(byKey1);

        //전체 데이터 출력
        System.out.println("== DB 삭제 확인 ==");
        List<PasswordInfo> all1 = passwordDAO.findAll();
        for (PasswordInfo passwordInfo : all1) {
            System.out.println(passwordInfo);
        }
    }
}
