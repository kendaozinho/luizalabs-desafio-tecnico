package com.luizalabs.customer.entrypoint.api.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizalabs.customer.entrypoint.controller.base.BaseControllerTest;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class BaseApiTest extends BaseControllerTest {
  @Autowired
  private ObjectMapper mapper;

  protected void getIsOk(String path) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  protected void getIsNotFound(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(404)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Not Found")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void getIsBadRequest(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Bad Request")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void postIsCreated(String path, Object request) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  protected void postIsUnprocessableEntity(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(422)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Unprocessable Entity")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void postIsBadRequest(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Bad Request")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void postIsConflict(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isConflict())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(409)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Conflict")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void postIsNotFound(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(404)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Not Found")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void putIsOk(String path, Object request) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  protected void putIsUnprocessableEntity(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(422)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Unprocessable Entity")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void putIsBadRequest(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Bad Request")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void putIsNotFound(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(404)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Not Found")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void putIsConflict(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isConflict())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(409)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Conflict")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void deleteIsNoContent(String path) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  protected void deleteIsBadRequest(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(400)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Bad Request")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void deleteIsNotFound(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(404)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Not Found")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void deleteIsUnprocessableEntity(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(422)))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is("Unprocessable Entity")))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }
}
