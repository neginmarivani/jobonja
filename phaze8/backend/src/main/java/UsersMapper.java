
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import io.jsonwebtoken.impl.TextCodec;
import org.json.JSONArray;
import org.json.JSONObject;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import io.jsonwebtoken.*;
import java.util.Date;

public class UsersMapper extends Mapper<User , Integer>{


	private static UsersMapper singlton_instance = null;

	@Override
	protected String getFindStatement(){
		return "SELECT * FROM USER ";
	}
	@Override
	protected User convertResultSetToDomainModel(ResultSet rs){
		return convertTableToUser(rs);
	}
	public static UsersMapper getInstance() {
		if (singlton_instance == null) {
			singlton_instance = new UsersMapper();
		}
		return singlton_instance;
	}

	Tools tools = Tools.getInstance();

	public UsersMapper() {
		try {
			getSkillsFromServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public User getUser(int id) {

		return getUserFromDB(id);
	}

	public void addEndorsement(int endorsedId, String skillName, int endoreserId) {
		Connection conn = ConnectDB.getConnetion();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO Endorsement VALUES (?,?,?)");
			st.setInt(1, endoreserId);
			st.setInt(2, endorsedId);
			st.setString(3, skillName);
			st.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}

	public boolean EndorseSkill(int endorsedId, String skillName, int endorserId) {

		if (!UserEndorsedThisSkillBefore(endorsedId, skillName, endorserId)) {

			addEndorsement(endorsedId, skillName, endorserId);
			return true;
		}
		return false;

	}

	private boolean UserEndorsedThisSkillBefore(int endorsedId, String skillName, int endorserId) {
		Connection conn = ConnectDB.getConnetion();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT COUNT(*) AS total FROM Endorsement WHERE endorsedId=" + Integer.toString(endorsedId)
							+ " AND endorserId=" + Integer.toString(endorserId) + " AND skillName='" + skillName + "'");
			int num = rs.getInt("total");
			if (num == 0) {
				return false;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}


		return true;
	}

	public ArrayList<User> getUsers() {
		ArrayList<User> users = new ArrayList<User>();
		Connection conn = ConnectDB.getConnetion();
		User u = null;
		try {
			Statement st = conn.createStatement();
			ResultSet usersTable = st.executeQuery("SELECT * FROM USER ");
			while (usersTable.next()) {
				u = convertTableToUser(usersTable);
				users.add(u);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return users;

	}

	public void getSkillsFromServer() throws IOException {
		String json = tools.getJsonStr("http://142.93.134.194:8000/joboonja/skill");
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonObject = jsonarray.getJSONObject(i);

			addSkillToDB(jsonObject.getString("name"));
		}
	}

	public void addUser(JSONObject jObject) {
		int id = jObject.getInt("ID");
		String userName = jObject.getString("first name");
		String userFamilyName = jObject.getString("family name");
		JSONArray skillsTemp = jObject.getJSONArray("skills");
		ArrayList<Skill> skills = tools.addSkill(skillsTemp);
		String jobTitle = jObject.getString("position");
		String imageUrl = jObject.getString("imgURL");
		String bio = jObject.getString("bio");

		User u = new User(id, bio, jobTitle, userName, userFamilyName, skills, imageUrl);
		// jobonjaUsers.add(u);
		addUserToDB(u,null);

	}

	public static String readFile(String filename) {
		String result = "";
		try {
			Reader reader = new InputStreamReader(new FileInputStream(filename), "utf-8");
			BufferedReader br = new BufferedReader(reader);

			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			while (line != null) {
				sb.append(line);
				line = br.readLine();
			}
			result = sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public void getUsersFromJsonFile() {
		String json = readFile("C:\\Users\\tehrani\\Desktop\\jobonja\\src\\main\\users.json");
		JSONArray jsonarray = new JSONArray(json);
		for (int i = 0; i < jsonarray.length(); i++) {
			JSONObject jsonobject = jsonarray.getJSONObject(i);
			addUser(jsonobject);

		}

	}

	public boolean deleteSkill(String skillName, int userId) {
		Connection conn = ConnectDB.getConnetion();
		try {
			Statement st = conn.createStatement();
			int num = st.executeUpdate(
					"DELETE FROM UserSkill WHERE userId=" + Integer.toString(userId) + " AND name='" + skillName + "'");
			if (num == 0) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return false;
	}

	public boolean addSkill(String skillName, int userId) {
		Connection conn = ConnectDB.getConnetion();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) AS total FROM UserSkill WHERE userId="
					+ Integer.toString(userId) + " AND name='" + skillName + "'");
			int num = rs.getInt("total");
			if (num == 0) {
				addUserSkillToDB(userId, new Skill(skillName, 0));
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return false;
	}

	public ArrayList<Skill> getUserSkill(int id) {
		ArrayList<Skill> out = new ArrayList<Skill>();
		Connection conn = ConnectDB.getConnetion();
		try {
			Statement st = conn.createStatement();
			ResultSet skillTable = st.executeQuery("SELECT * FROM UserSkill WHERE userId=" + Integer.toString(id));
			while (skillTable.next()) {
				String name = skillTable.getString("name");
				int point = skillTable.getInt("point");
				out.add(new Skill(name, point));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return out;
	}

	public User getUserFromDB(int id) {
		Connection conn = ConnectDB.getConnetion();
		User u = null;
		try {
			Statement st = conn.createStatement();
			ResultSet userTable = st.executeQuery("SELECT * FROM USER WHERE id=" + Integer.toString(id));
			u = convertTableToUser(userTable);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return u;
	}

	public ArrayList<User> getUserFromDB(String query) {
		String arr[] = query.split(" ");
		String name = arr[0];
		String familyName = arr[1];
		ArrayList<User> out = new ArrayList<User>();
		Connection conn = ConnectDB.getConnetion();
		try {
			Statement st = conn.createStatement();
			ResultSet userTable = st.executeQuery(
			" SELECT * FROM USER WHERE userFamilyName LIKE '%" + familyName + "%' OR userName LIKE '%" + name + "%'");
			while (userTable.next()) {
				out.add(convertTableToUser(userTable));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return out;
	}

	private User convertTableToUser(ResultSet userTable) {
		User u = null;
		try {
			ArrayList<Skill> selectedSkill = getUserSkill(userTable.getInt("id"));
			u = new User(userTable.getInt("id"), userTable.getString("bio"), userTable.getString("jobTitle"),
					userTable.getString("userName"), userTable.getString("userFamilyName"), selectedSkill,
					userTable.getString("ImageURL"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return u;
	}

	public void addSkillToDB(String skill) {
		Connection conn = ConnectDB.getConnetion();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO SystemSkill VALUES (?)");
			st.setString(1, skill);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}


	}

	public void addUserSkillToDB(int userID, Skill skill) {

		Connection conn = ConnectDB.getConnetion();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO UserSkill VALUES (?,?,?)");
			st.setString(1, skill.getName());
			st.setInt(2, skill.getPoint());
			st.setInt(3, userID);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}

	public void addUserToDB(User u,String authName) {

		Connection conn = ConnectDB.getConnetion();
		try {
			if(u.getSkills()!=null) {
				for (int i = 0; i < u.getSkills().size(); i++) {
					addUserSkillToDB(u.getId(), u.getSkills().get(i));
				}
			}
			PreparedStatement st = conn.prepareStatement("INSERT INTO USER VALUES (?,?,?,?,?,?,?,?)");
			st.setInt(1, u.getId());
			st.setString(2, u.getBio());
			st.setString(3, u.getJobTitle());
			st.setString(4, u.getUserFamilyName());
			st.setString(5, u.getImageUrl());
			st.setInt(6, 0);
			st.setString(7, u.getUserName());
			st.setString(8,authName);
			st.executeUpdate();
			// this.getUserFromDB(1);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}

	public ArrayList<User> searchUser(String query) {

		return getUserFromDB(query);
	}
	public int getLastId(){
		Connection conn = ConnectDB.getConnetion();
		int maxId=0;
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(id) FROM USER " );
			rs.next();
			maxId = rs.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}


		return maxId;
	}
	public boolean isRepeatedUserName(String name){
		Connection conn = ConnectDB.getConnetion();
		try {
			Statement st = conn.createStatement();
			ResultSet rs  = st.executeQuery("SELECT * FROM LogedInUserInfo WHERE userName='" + name+"'");
			if (!rs.next()){
				return false;
			}
			else{
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	public String calHash(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	public void addLogedInInfoToDB(String userName,String passWord){
		Connection conn = ConnectDB.getConnetion();
		try {
			PreparedStatement st = conn.prepareStatement("INSERT INTO LogedInUserInfo VALUES (?,?)");
			st.setString(1, userName);
			st.setString(2, passWord);
			st.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

	}
	public boolean isUserAndPassCorrect(String userName,String hashPass){
		Connection conn = ConnectDB.getConnetion();
		try {
			Statement st = conn.createStatement();
			ResultSet rs  = st.executeQuery("SELECT * FROM LogedInUserInfo WHERE userName='" + userName+"' AND passWord='"+ hashPass +"'");
			if (!rs.next()){
				return false;
			}
			else{
				return true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	public User getUserbyUserName(String userName){
		Connection conn = ConnectDB.getConnetion();
		User u = null;
		try {
			Statement st = conn.createStatement();
			ResultSet userTable = st.executeQuery("SELECT * FROM USER WHERE authName ='"+userName +"'");
			u = convertTableToUser(userTable);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
//		try {
//			conn.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}

		return u;
	}
	public String createJWT(String userName) {
		long ttlMillis =300;
		//The JWT signature algorithm we will be using to sign the token
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

//		//We will sign our JWT with our ApiKey secret
//		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("joboonja");
//		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		//Let's set the JWT Claims
		JwtBuilder builder = Jwts.builder().setId(userName)
				.setIssuedAt(now)
				.setSubject("logedIn")
				.setIssuer("server")
				.signWith(signatureAlgorithm, TextCodec.BASE64.decode("joboonja"));

		//if it has been specified, let's add the expiration
//		if (ttlMillis >= 0) {
//			long expMillis = nowMillis + ttlMillis;
//			Date exp = new Date(expMillis);
//			builder.setExpiration(exp);
//		}

		//Builds the JWT and serializes it to a compact, URL-safe string
		return builder.compact();
	}
	public String parseJWT(String jwt) {
		//This line will throw an exception if it is not a signed JWS (as expected)
		try{
		Claims claims = Jwts.parser()
				.setSigningKey(TextCodec.BASE64.decode("joboonja"))
				.parseClaimsJws(jwt).getBody();
		System.out.println("ID: " + claims.getId());
		System.out.println("Subject: " + claims.getSubject());
		System.out.println("Issuer: " + claims.getIssuer());
		System.out.println("Expiration: " + claims.getExpiration());
		return claims.getId();

		} catch (SignatureException e){
			System.out.println("SignatureException  ");
			return null;
		}
		catch (MalformedJwtException e){
			System.out.println("MalformedJwtException  ");
			return null;
		}
		catch (ExpiredJwtException e){
			System.out.println("ExpiredJwtException  ");
			return null;
		}
		catch (UnsupportedJwtException e){
			System.out.println("UnsupportedJwtException  ");
			return null;
		}
		catch (IllegalArgumentException e){
			System.out.println("IllegalArgumentException  ");
			return null;
		}

	}
	public HttpServletResponse setErrorMsg(HttpServletResponse response , String msg, int code)throws IOException{
		JSONObject jsonObject;
		String myJson;
		jsonObject = new JSONObject();
		jsonObject.put("msg", msg);
		myJson = jsonObject.toString();
		response.setStatus(code);
		response.setContentType("application/json; charset=UTF-8;");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(myJson);
		return response;
	}
	public int getAuthentication(HttpServletRequest request , String jwt){
		String userName ="";
		if(jwt != null){
			userName = parseJWT(jwt);
		}

		return getUserbyUserName(userName).getId();
	}
}