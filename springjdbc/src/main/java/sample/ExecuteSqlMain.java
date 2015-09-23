package sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import sample.config.DataSourceConfig;
import sample.config.TemplateConfig;
import sample.springjdbc.business.domain.Owner;
import sample.springjdbc.business.domain.Pet;

public class ExecuteSqlMain {

    public static void main(String[] args) {
    	
    	// Springのコンテナを生成        
    	//XMLでBean定義した場合
    	ApplicationContext ctx = new ClassPathXmlApplicationContext("sample/config/spring-db.xml");

    	//JavaConfigでBean定義した場合
    	//ApplicationContext ctx = new AnnotationConfigApplicationContext(
        //        TemplateConfig.class, DataSourceConfig.class);
        
        // JdbcTemplate と NamedParameterJdbcTemplateのオブジェクトを取得
        JdbcTemplate jdbcTemplate = ctx.getBean(JdbcTemplate.class);
        NamedParameterJdbcTemplate npJdbcTemplate = ctx.getBean(NamedParameterJdbcTemplate.class);

        // SELECT 文　～ドメインへ変換しない場合
        // queryForInt メソッド
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM PET", Integer.class);
        System.out.println(count);

        
        String ownerName = "東京太郎";
        count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM PET WHERE OWNER_NAME=?", Integer.class, ownerName);
        System.out.println(count);
        
        // queryForObject メソッド
        int id = 1;
        String petName = jdbcTemplate.queryForObject(
                "SELECT PET_NAME FROM PET WHERE PET_ID=?", String.class, id);
        System.out.println(petName);
        
        Date birthDate = jdbcTemplate.queryForObject(
                "SELECT BIRTH_DATE FROM PET WHERE PET_ID=?", Date.class, id);
        System.out.println(birthDate);
        
        
        // queryForMap メソッド
        Map<String, Object> map = jdbcTemplate.queryForMap(
                "SELECT * FROM PET WHERE PET_ID=?", id);
        System.out.println(map.get("PET_NAME"));
        System.out.println(map.get("OWNER_NAME"));
        
        // queryForList メソッド
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(
                " SELECT * FROM PET WHERE OWNER_NAME=?", ownerName);
        System.out.println(mapList.get(0).get("PET_NAME"));
        System.out.println(mapList.get(0).get("OWNER_NAME"));
        
        
        List<Integer> priceList = jdbcTemplate.queryForList(
                "SELECT PRICE FROM PET WHERE OWNER_NAME=?", Integer.class, ownerName);
        
        System.out.println(priceList.get(0));
        
        
        // SELECT 文　～ドメインへ変換する場合
        // queryForObject メソッド
        // RowMapperを匿名クラスにする場合
        Pet pet = jdbcTemplate.queryForObject(
                "SELECT * FROM PET WHERE PET_ID=?"
                , new RowMapper<Pet>() {
                    public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Pet p = new Pet();
                        p.setPetId((Integer)rs.getObject("PET_ID"));
                        p.setPetName(rs.getString("PET_NAME"));
                        p.setOwnerName(rs.getString("OWNER_NAME"));
                        p.setPrice((Integer)rs.getObject("PRICE"));
                        p.setBirthDate(rs.getDate("BIRTH_DATE"));
                        return p;
                        }}
                , id); 
        System.out.println(pet.getPetName());
        System.out.println(pet.getOwnerName());
        
        // RowMapperを匿名クラスにしない場合
        class MyRowMapper implements RowMapper<Pet> {
            public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pet p = new Pet();
                p.setPetId(rs.getInt("PET_ID"));
                p.setPetName(rs.getString("PET_NAME"));
                p.setOwnerName(rs.getString("OWNER_NAME"));
                p.setPrice(rs.getInt("PRICE"));
                p.setBirthDate(rs.getDate("BIRTH_DATE"));
                return p;
            }
        }
        pet = jdbcTemplate.queryForObject(
                " SELECT * FROM PET WHERE PET_ID=?"
                ,new MyRowMapper()
                ,id);
        System.out.println(pet.getPetName());
        System.out.println(pet.getOwnerName());
        
        // query メソッド
        List<Pet> petList = jdbcTemplate.query(
                " SELECT * FROM PET WHERE OWNER_NAME=?"
                , new RowMapper<Pet>() {
                public Pet mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Pet p = new Pet();
                    p.setPetId(rs.getInt("PET_ID"));
                    p.setPetName(rs.getString("PET_NAME"));
                    p.setOwnerName(rs.getString("OWNER_NAME"));
                    p.setPrice(rs.getInt("PRICE"));
                p.setBirthDate(rs.getDate("BIRTH_DATE"));
                    return p;
                }}
            , ownerName);
        System.out.println(petList.get(0).getPetName());
        System.out.println(petList.get(0).getOwnerName());

        // BeanPropertyRowMapperを使用してドメインへの変更を自動化
        pet = jdbcTemplate.queryForObject(
                " SELECT * FROM PET WHERE PET_ID=?"
                , new BeanPropertyRowMapper<Pet>(Pet.class)
                , id);
        System.out.println(pet.getPetName());
        System.out.println(pet.getOwnerName());

        // ResultSetExtractorを使用したドメインの変換
        // 親のドメインが１つの場合
        Owner owner = jdbcTemplate.query(
                " SELECT * FROM OWNER O INNER JOIN PET P ON O.OWNER_NAME=P.OWNER_NAME WHERE O.OWNER_NAME=?"
                , new ResultSetExtractor<Owner>() {
                    public Owner extractData(ResultSet rs) throws SQLException, DataAccessException {
                        if (!rs.next()) {
                            return null;
                        }
                        Owner owner = new Owner();
                        owner.setOwnerName(rs.getString("OWNER_NAME"));
                        do {
                            Pet pet = new Pet();
                            pet.setPetId(rs.getInt("PET_ID"));
                            pet.setPetName(rs.getString("PET_NAME"));
                            pet.setOwnerName(rs.getString("OWNER_NAME"));
                            pet.setPrice(rs.getInt("PRICE"));
                            pet.setBirthDate(rs.getDate("BIRTH_DATE"));
                            owner.getPetList().add(pet);
                        } while(rs.next());
                        return owner;
                    }}
                , ownerName);
        System.out.println(owner.getOwnerName());
        System.out.println(owner.getPetList().get(0).getPetName());
        System.out.println(owner.getPetList().get(0).getOwnerName());
        
        // 親のドメインが複数の場合
        List<Owner> ownerList = jdbcTemplate.query(
                " SELECT * FROM OWNER O INNER JOIN PET P ON O.OWNER_NAME=P.OWNER_NAME ORDER BY OWNER_NAME"
                , new ResultSetExtractor<List<Owner>>() {
                    public List<Owner> extractData(ResultSet rs) throws SQLException, DataAccessException {
                        List<Owner> result = new ArrayList<Owner>();
                        Owner owner = null;
                        String currentPk = "";
                        while (rs.next()) {
                            String ownerName = rs.getString("OWNER_NAME");
                            if (!ownerName.equals(currentPk)) {
                                owner = new Owner();
                                owner.setOwnerName(rs.getString("OWNER_NAME"));
                                currentPk = ownerName;
                                result.add(owner);
                            }
                            Pet pet = new Pet();
                            pet.setPetId(rs.getInt("PET_ID"));
                            pet.setPetName(rs.getString("PET_NAME"));
                            pet.setOwnerName(rs.getString("OWNER_NAME"));
                            pet.setPrice(rs.getInt("PRICE"));
                            pet.setBirthDate(rs.getDate("BIRTH_DATE"));
                            owner.getPetList().add(pet);
                        }
                        return result;
                    }}
                );
        System.out.println(ownerList.get(0).getOwnerName());
        System.out.println(ownerList.get(0).getPetList().get(0).getPetName());
        System.out.println(ownerList.get(0).getPetList().get(0).getOwnerName());
        
        
        // INSERT/UPDATE/DELETE 文
        pet = new Pet();
        pet.setPetId(99);
        pet.setPetName("タマ");
        pet.setOwnerName("東京太郎");
        pet.setPrice(10000);
        pet.setBirthDate(new Date());        
        jdbcTemplate.update(
                "INSERT INTO PET (PET_ID, PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE) VALUES (?, ?, ?, ?, ?)"
                , pet.getPetId(), pet.getPetName(), pet.getOwnerName(), pet.getPrice(), pet.getBirthDate());
        
        jdbcTemplate.update(
                "UPDATE PET SET PET_NAME=?, OWNER_NAME=?, PRICE=?, BIRTH_DATE=? WHERE PET_ID=?"
                , pet.getPetName(), pet.getOwnerName(), pet.getPrice(), pet.getBirthDate(), pet.getPetId());
        
        jdbcTemplate.update("DELETE FROM PET WHERE PET_ID=?", pet.getPetId());

        // NamedParameterJdbcTemplateを使用
        // メソッドチェーンで記述する場合
        npJdbcTemplate.update(
                " INSERT INTO PET (PET_ID, PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE)" +
                    " VALUES (:PET_ID, :PET_NAME, :OWNER_NAME, :PRICE, :BIRTH_DATE)"
                , new MapSqlParameterSource()
                .addValue("PET_ID", pet.getPetId())
                .addValue("PET_NAME", pet.getPetName())
                .addValue("OWNER_NAME", pet.getOwnerName())
                .addValue("PRICE", pet.getPrice())
                .addValue("BIRTH_DATE", pet.getBirthDate())
            );
        
        jdbcTemplate.update("DELETE FROM PET WHERE PET_ID=?", pet.getPetId());
        
        // メソッドチェーンで記述しない場合
        MapSqlParameterSource map2 = new MapSqlParameterSource();
        map2.addValue("PET_ID", pet.getPetId());
        map2.addValue("PET_NAME", pet.getPetName());
        map2.addValue("OWNER_NAME", pet.getOwnerName());
        map2.addValue("PRICE", pet.getPrice());
        map2.addValue("BIRTH_DATE", pet.getBirthDate());
        npJdbcTemplate.update(
            " INSERT INTO PET (PET_ID, PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE)" +
                " VALUES (:PET_ID, :PET_NAME, :OWNER_NAME, :PRICE, :BIRTH_DATE)"
            ,map2
        );
        
        jdbcTemplate.update("DELETE FROM PET WHERE PET_ID=?", pet.getPetId());

        // BeanPropertySqlParameterSourceを使用し、ドメインからパラメータへの変換を自動化
        BeanPropertySqlParameterSource beanProps = new BeanPropertySqlParameterSource(pet);
        npJdbcTemplate.update(
            " INSERT INTO PET (PET_ID, PET_NAME, OWNER_NAME, PRICE, BIRTH_DATE)" +
                " VALUES (:petId, :petName, :ownerName, :price, :birthDate)"
            ,beanProps
        );
        
        jdbcTemplate.update("DELETE FROM PET WHERE PET_ID=?", pet.getPetId());
        
        // バッチアップデート、プロシージャコール
        // batchUpdate メソッドを使用したバッチアップデート
        // 更新データとして、Objectの配列を使用する場合
        List<Object[]> argArray = new ArrayList<Object[]>();
        argArray.add(new Object[]{"owner02", 2});
        argArray.add(new Object[]{"owner03", 3});
        argArray.add(new Object[]{"owner04", 4});
        argArray.add(new Object[]{"owner05", 5});
        
        int[] num = jdbcTemplate.batchUpdate(
            " UPDATE PET SET OWNER_NAME=? WHERE PET_ID=?"
            , argArray);

        // 更新データとして、MapSqlParameterSourceを使用する場合
        List<MapSqlParameterSource> mList = new ArrayList<MapSqlParameterSource>();
        MapSqlParameterSource m1 = new MapSqlParameterSource()
        .addValue("PET_ID", 2)
        .addValue("OWNER_NAME", "owner002");
        mList.add(m1);
        MapSqlParameterSource m2 = new MapSqlParameterSource()
        .addValue("PET_ID", 3)
        .addValue("OWNER_NAME", "owner003");
        mList.add(m2);
        MapSqlParameterSource m3 = new MapSqlParameterSource()
        .addValue("PET_ID", 4)
        .addValue("OWNER_NAME", "owner004");
        mList.add(m3);
        MapSqlParameterSource m4 = new MapSqlParameterSource()
        .addValue("PET_ID", 5)
        .addValue("OWNER_NAME", "owner005");
        mList.add(m4);
        num = npJdbcTemplate.batchUpdate(
                " UPDATE PET SET OWNER_NAME=:OWNER_NAME WHERE PET_ID=:PET_ID"
                , mList.toArray(new MapSqlParameterSource[]{}));
        
        
        // SimpleJdbcCallを使用したプロシージャコール
        SimpleJdbcCall call = new SimpleJdbcCall(jdbcTemplate.getDataSource())
        .withProcedureName("CALC_PET_PRICE")
        .withoutProcedureColumnMetaDataAccess()
        .declareParameters(
            new SqlParameter("IN_PET_ID", Types.INTEGER),
            new SqlOutParameter("OUT_PRICE", Types.INTEGER)
        );        
        MapSqlParameterSource in = new MapSqlParameterSource()
                        .addValue("IN_PET_ID", id);
        Map<String, Object> out = call.execute(in);
        int price = (Integer)out.get("OUT_PRICE");
        System.out.println(price);
    }
    
}
