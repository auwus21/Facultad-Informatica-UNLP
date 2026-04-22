package ar.edu.unlp.info.oo2.biblioteca;

import java.util.List;
import org.json.simple.*;

public class JsonSimpleAdapter implements Exporter{
	
	@Override
    public String exportar(List<Socio> socios) {
		JSONArray jsonArray = new JSONArray();
		for (Socio socio : socios) {
			JSONObject socioJson = new JSONObject();
			socioJson.put("nombre", socio.getNombre());
			socioJson.put("legajo", socio.getLegajo());
			socioJson.put("email", socio.getEmail());

			jsonArray.add(socioJson);
		}
		
		return jsonArray.toJSONString();    }
	

}
