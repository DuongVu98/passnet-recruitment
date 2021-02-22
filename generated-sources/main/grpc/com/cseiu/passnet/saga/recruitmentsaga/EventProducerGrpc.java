package com.cseiu.passnet.saga.recruitmentsaga;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.19.0)",
    comments = "Source: produce-events.proto")
public final class EventProducerGrpc {

  private EventProducerGrpc() {}

  public static final String SERVICE_NAME = "produceEvents.EventProducer";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceCreateClassEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProduceCreateClassEvent",
      requestType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent.class,
      responseType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceCreateClassEventMethod() {
    io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceCreateClassEventMethod;
    if ((getProduceCreateClassEventMethod = EventProducerGrpc.getProduceCreateClassEventMethod) == null) {
      synchronized (EventProducerGrpc.class) {
        if ((getProduceCreateClassEventMethod = EventProducerGrpc.getProduceCreateClassEventMethod) == null) {
          EventProducerGrpc.getProduceCreateClassEventMethod = getProduceCreateClassEventMethod = 
              io.grpc.MethodDescriptor.<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "produceEvents.EventProducer", "ProduceCreateClassEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EventProducerMethodDescriptorSupplier("ProduceCreateClassEvent"))
                  .build();
          }
        }
     }
     return getProduceCreateClassEventMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EventProducerStub newStub(io.grpc.Channel channel) {
    return new EventProducerStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EventProducerBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EventProducerBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EventProducerFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EventProducerFutureStub(channel);
  }

  /**
   */
  public static abstract class EventProducerImplBase implements io.grpc.BindableService {

    /**
     */
    public void produceCreateClassEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProduceCreateClassEventMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getProduceCreateClassEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent,
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>(
                  this, METHODID_PRODUCE_CREATE_CLASS_EVENT)))
          .build();
    }
  }

  /**
   */
  public static final class EventProducerStub extends io.grpc.stub.AbstractStub<EventProducerStub> {
    private EventProducerStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventProducerStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventProducerStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventProducerStub(channel, callOptions);
    }

    /**
     */
    public void produceCreateClassEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProduceCreateClassEventMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EventProducerBlockingStub extends io.grpc.stub.AbstractStub<EventProducerBlockingStub> {
    private EventProducerBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventProducerBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventProducerBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventProducerBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse produceCreateClassEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent request) {
      return blockingUnaryCall(
          getChannel(), getProduceCreateClassEventMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EventProducerFutureStub extends io.grpc.stub.AbstractStub<EventProducerFutureStub> {
    private EventProducerFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EventProducerFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected EventProducerFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EventProducerFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> produceCreateClassEvent(
        com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getProduceCreateClassEventMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PRODUCE_CREATE_CLASS_EVENT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EventProducerImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EventProducerImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_PRODUCE_CREATE_CLASS_EVENT:
          serviceImpl.produceCreateClassEvent((com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.CreateClassEvent) request,
              (io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class EventProducerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    EventProducerBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("EventProducer");
    }
  }

  private static final class EventProducerFileDescriptorSupplier
      extends EventProducerBaseDescriptorSupplier {
    EventProducerFileDescriptorSupplier() {}
  }

  private static final class EventProducerMethodDescriptorSupplier
      extends EventProducerBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    EventProducerMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EventProducerGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EventProducerFileDescriptorSupplier())
              .addMethod(getProduceCreateClassEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
