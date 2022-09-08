import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class CRUD_ApiLocal {

    @BeforeTest
    public void setUp(){
        baseURI = "http://localhost:3000/";
    }

    @Test
    public void getUsers(){
        when().
                get("/users").
        then().
                statusCode(200).
                body("job[1]", equalTo("DevOps")).
                body("firstName", hasItems("Jon","Beto","Luisa","Sarah")).
                log().all();
    }

    @Test
    public void getSubjects(){
        given().
                param("name", "DevOps").
                get("/subjects").
                then().
                statusCode(200).
                log().all();
    }

    @Test
    public void postUser(){
        JSONObject request = new JSONObject();

        request.put("firstName","Luis");
        request.put("lastName","Gomez");
        request.put("job","Customer Success");
        request.put("subjectId","3");

        given().
                contentType(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/users").
        then().
                statusCode(201).
                log().all();
    }

    @Test
    public void patchUser(){
        JSONObject request = new JSONObject();

        request.put("firstName","Carolina");
        request.put("lastName","Lopez");

        given().
                contentType(ContentType.JSON).
                body(request.toJSONString()).
        when().
                patch("/users/3").
        then().
                statusCode(200).
                log().all();
    }

    @Test
    public void deleteUser(){
        when().
                delete("/users/5").
        then().
                statusCode(200).
                log().all();
    }

}
