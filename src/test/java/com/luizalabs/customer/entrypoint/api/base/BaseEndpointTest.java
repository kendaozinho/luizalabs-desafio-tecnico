package com.luizalabs.customer.entrypoint.api.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizalabs.customer.entrypoint.base.BaseControllerTest;
import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class BaseEndpointTest extends BaseControllerTest {
  @Autowired
  private ObjectMapper mapper;

  protected <T> T getIsOk(String path, Class<T> responseType) throws Throwable {
    String response = super.mock.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    return this.mapper.readValue(response, responseType);
  }

  protected void getIsNotFound(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.NOT_FOUND.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.NOT_FOUND.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void getIsBadRequest(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.get(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.BAD_REQUEST.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.BAD_REQUEST.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected <T> T postIsCreated(String path, Object request, Class<T> responseType) throws Throwable {
    String response = super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andReturn().getResponse().getContentAsString();

    return this.mapper.readValue(response, responseType);
  }

  protected void postIsUnprocessableEntity(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void postIsBadRequest(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.BAD_REQUEST.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.BAD_REQUEST.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void postIsConflict(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isConflict())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.CONFLICT.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.CONFLICT.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void postIsNotFound(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.NOT_FOUND.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.NOT_FOUND.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void postIsInternalServerError(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isInternalServerError())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.INTERNAL_SERVER_ERROR.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void postIsBadGateway(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.post(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isBadGateway())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.BAD_GATEWAY.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.BAD_GATEWAY.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected <T> T putIsOk(String path, Object request, Class<T> responseType) throws Throwable {
    String response = super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andReturn().getResponse().getContentAsString();

    return this.mapper.readValue(response, responseType);
  }

  protected void putIsUnprocessableEntity(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void putIsBadRequest(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.BAD_REQUEST.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.BAD_REQUEST.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void putIsNotFound(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.NOT_FOUND.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.NOT_FOUND.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void putIsConflict(String path, Object request, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.put(path).accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON).content(this.mapper.writeValueAsString(request)))
        .andExpect(MockMvcResultMatchers.status().isConflict())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.CONFLICT.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.CONFLICT.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void deleteIsNoContent(String path) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());
  }

  protected void deleteIsBadRequest(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.BAD_REQUEST.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.BAD_REQUEST.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void deleteIsNotFound(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.NOT_FOUND.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.NOT_FOUND.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }

  protected void deleteIsUnprocessableEntity(String path, String details) throws Throwable {
    super.mock.perform(MockMvcRequestBuilders.delete(path).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
        .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY.value())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.message", Matchers.is(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())))
        .andExpect(MockMvcResultMatchers.jsonPath("$.details", Matchers.is(details)));
  }
}
