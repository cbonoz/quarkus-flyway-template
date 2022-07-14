package com.stavvy.resources;

import com.stavvy.models.MeetingPayload;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/meetings")
public class MeetingResource {

    @POST
    public Response createMeeting(MeetingPayload payload) {
//        Room room = Room.creator().setUniqueName(payload.getName()).setRecordParticipantsOnConnect(true).create();
        return Response.ok(payload).build();
    }
}