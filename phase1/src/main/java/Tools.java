import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class Tools {

    public ArrayList<Skill> addSkill(JSONArray skillsTemp){

        ArrayList<Skill> SkillTemp = new ArrayList<Skill>();
        for (int i = 0; i < skillsTemp.length(); i++) {
            JSONObject jo = skillsTemp.getJSONObject(i);
            String name = jo.getString("name");
            int points = jo.getInt("point");
            SkillTemp.add(new Skill(name, points));

        }
        return SkillTemp;
    }
    public String getJsonStr(String link)throws IOException {
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(link);

        HttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();

        String json = EntityUtils.toString(entity);
        return json;
    }


}
