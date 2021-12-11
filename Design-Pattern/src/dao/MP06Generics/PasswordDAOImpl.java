package dao.MP06Generics;


import java.sql.*;


public class PasswordDAOImpl extends DAOImpl<PasswordInfo, String> {

    final static String DB_FILE_NAME = "passwords.db";


    Connection connection = null;
    Statement statement = null;

    public PasswordDAOImpl(String dbTableName) {
        super(dbTableName); //기본 세팅, 연결 및 테이블 생성
        final String table = "(url text PRIMARY KEY , id text, password text)"; //테이블 생성 String

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:"+ DB_FILE_NAME); //jdbc 연결

            statement = connection.createStatement();

            statement.setQueryTimeout(30);

            statement.executeUpdate("DROP TABLE IF EXISTS "+ dbTableName);
            statement.executeUpdate("CREATE TABLE "+dbTableName + table);

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public Statement getStatement() {
        return statement;
    }

    @Override
    public String getInsertValueStr(PasswordInfo data){
        String fmt = "'%s', '%s', '%s'";
        return String.format(fmt, data.getKey(), data.getId(), data.getPassword());
    }

    @Override
    public String getUpdateValueStr(PasswordInfo data) {
        String fmt = "id = '%s' , password = '%s'";
        return String.format(fmt, data.getId(), data.getPassword());
    }

    @Override
    public String getKeyColumnName() {
        return "url";
    }

    @Override
    public PasswordInfo getNewData(ResultSet rs) {
        PasswordInfo p = null;
        if( rs!= null){
            try {
                p = new PasswordInfo(rs.getString("url"),
                        rs.getString("id"), rs.getString("password"));
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
        return p;
    }



}
