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
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
// JAX-RS
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.google.gson.Gson;

@RequestScoped
@Path("/orders")
public class OrderResource {
  private static final Logger logger = LogManager.getLogger(OrderResource.class);

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/create")
  public Response CreateOrder(String order_request) {
    Gson gson = new Gson();
    Order new_order = gson.fromJson(order_request, Order.class);

    new_order.updateAtCreation();

    DBHandler dbHandle = new DBHandler();
    int order_id = dbHandle.insertOrderDB(new_order);

    new_order.setOrderId(order_id);

    String resp = new_order.toJSON();

    return Response.ok(resp, MediaType.APPLICATION_JSON).status(201).build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response getOrderStatus(
      @PathParam("id") int order_id) {
    DBHandler DBHandle = new DBHandler();
    Order order = DBHandle.FetchOrderDB(order_id);
    System.out.println("Found an order with amount:");
    System.out.println(order.totalAmount);
    return Response.ok().build();
  }

}
