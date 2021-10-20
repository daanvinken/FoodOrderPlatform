// tag::copyright[]
/*******************************************************************************
 * Copyright (c) 2017, 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - Initial implementation
 *******************************************************************************/
 // end::copyright[]
package io.openliberty.guides.order;
import io.openliberty.guides.order.model.Order;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
// JAX-RS
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@RequestScoped
@Path("/orders")
public class OrderResource {
  private static final Logger logger = LogManager.getLogger(OrderResource.class);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/create")
  public Response CreateOrder(Order order) {
    System.out.println(String.format("Received new order (user_id = %d)", order.userId));
    logger.info(order.toString());
    System.out.println(order.toString());
    System.out.println("Total amount of order:");
    System.out.println(order.getTotalAmount());
    System.out.println("Pushing order to DB.");

    OrderHandler.insertOrderDB(order);

    String resp = "{\n" +
            "  \"status\": \"placed\",\n" +
            "  \"id\": 2,\n" +
            "  \"restaurantId\": 1,\n" +
            "  \"totalAmount\": 32.97,\n" +
            "  \"userId\": 4,\n" +
            "  \"updatedAt\": \"2020-06-03T09:00:58.034Z\",\n" +
            "  \"createdAt\": \"2020-06-03T09:00:58.034Z\"\n" +
            "}";
    return Response.ok(resp, MediaType.APPLICATION_JSON).status(201).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response getOrderStatus(
      @PathParam("id") int order_id) {
    return Response.ok("Hallootjes").build();
  }

}
