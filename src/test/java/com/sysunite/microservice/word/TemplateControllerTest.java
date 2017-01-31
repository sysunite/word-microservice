package com.sysunite.microservice.word;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

/**
 * @author Mohamad Alamili
 */

// The order is important because test1 will upload a template that test2 will check for downloading
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TemplateControllerTest extends BaseTest {

  private static String addedTemplateId;
  
  @Test
  public void test1_addTemplate() throws IOException {
    // Clear templates folder
    File templatesDir = new File("templates");
    for(File f : templatesDir.listFiles()){
      f.delete();
    }
    assertEquals(0, templatesDir.listFiles().length);
      
    // Do upload
    File uploaded_file = new File(Resources.getResource("upload.txt").getPath());
    
    addedTemplateId = 
    given().
      multiPart("uploaded_file", uploaded_file).
    when().
      post("/template/add").
    then().
      assertThat().
      statusCode(200).
    extract().
      response().
      asString();

    // Check file has been uploaded in directory
    assertEquals(1, templatesDir.listFiles().length);
    
    File templateFile = templatesDir.listFiles()[0];
    assertEquals(templateFile.getName(), addedTemplateId);
    
    // Assert contents are equal
    assertEquals(Files.toString(templateFile, StandardCharsets.UTF_8), Files.toString(uploaded_file, StandardCharsets.UTF_8));
  }

  @Test
  public void test2_getTemplate() throws IOException {
    String fileName = "hello.txt";
    
    String templateContent = 
    given().
      queryParam("templateId", addedTemplateId).
      queryParam("fileName", fileName).
    when().
      get("/template/get").
    then().
      assertThat().
      contentType("application/octet-stream").
      header("Content-Disposition", "attachment; filename=" + fileName).
      statusCode(200).
    extract().
      response().
      asString();

    // Check content
    String actualContent = Files.toString(new File("templates", addedTemplateId), StandardCharsets.UTF_8);
    assertEquals(templateContent, actualContent);
  }
}