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
  private static volatile io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProducePostNewJobEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProducePostNewJobEvent",
      requestType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent.class,
      responseType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProducePostNewJobEventMethod() {
    io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProducePostNewJobEventMethod;
    if ((getProducePostNewJobEventMethod = EventProducerGrpc.getProducePostNewJobEventMethod) == null) {
      synchronized (EventProducerGrpc.class) {
        if ((getProducePostNewJobEventMethod = EventProducerGrpc.getProducePostNewJobEventMethod) == null) {
          EventProducerGrpc.getProducePostNewJobEventMethod = getProducePostNewJobEventMethod = 
              io.grpc.MethodDescriptor.<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "produceEvents.EventProducer", "ProducePostNewJobEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EventProducerMethodDescriptorSupplier("ProducePostNewJobEvent"))
                  .build();
          }
        }
     }
     return getProducePostNewJobEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceAcceptStudentApplicationEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProduceAcceptStudentApplicationEvent",
      requestType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent.class,
      responseType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceAcceptStudentApplicationEventMethod() {
    io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceAcceptStudentApplicationEventMethod;
    if ((getProduceAcceptStudentApplicationEventMethod = EventProducerGrpc.getProduceAcceptStudentApplicationEventMethod) == null) {
      synchronized (EventProducerGrpc.class) {
        if ((getProduceAcceptStudentApplicationEventMethod = EventProducerGrpc.getProduceAcceptStudentApplicationEventMethod) == null) {
          EventProducerGrpc.getProduceAcceptStudentApplicationEventMethod = getProduceAcceptStudentApplicationEventMethod = 
              io.grpc.MethodDescriptor.<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "produceEvents.EventProducer", "ProduceAcceptStudentApplicationEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EventProducerMethodDescriptorSupplier("ProduceAcceptStudentApplicationEvent"))
                  .build();
          }
        }
     }
     return getProduceAcceptStudentApplicationEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceRemoveStudentApplicationEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProduceRemoveStudentApplicationEvent",
      requestType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent.class,
      responseType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceRemoveStudentApplicationEventMethod() {
    io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceRemoveStudentApplicationEventMethod;
    if ((getProduceRemoveStudentApplicationEventMethod = EventProducerGrpc.getProduceRemoveStudentApplicationEventMethod) == null) {
      synchronized (EventProducerGrpc.class) {
        if ((getProduceRemoveStudentApplicationEventMethod = EventProducerGrpc.getProduceRemoveStudentApplicationEventMethod) == null) {
          EventProducerGrpc.getProduceRemoveStudentApplicationEventMethod = getProduceRemoveStudentApplicationEventMethod = 
              io.grpc.MethodDescriptor.<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "produceEvents.EventProducer", "ProduceRemoveStudentApplicationEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EventProducerMethodDescriptorSupplier("ProduceRemoveStudentApplicationEvent"))
                  .build();
          }
        }
     }
     return getProduceRemoveStudentApplicationEventMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceDeleteJobEventMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ProduceDeleteJobEvent",
      requestType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent.class,
      responseType = com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent,
      com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceDeleteJobEventMethod() {
    io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> getProduceDeleteJobEventMethod;
    if ((getProduceDeleteJobEventMethod = EventProducerGrpc.getProduceDeleteJobEventMethod) == null) {
      synchronized (EventProducerGrpc.class) {
        if ((getProduceDeleteJobEventMethod = EventProducerGrpc.getProduceDeleteJobEventMethod) == null) {
          EventProducerGrpc.getProduceDeleteJobEventMethod = getProduceDeleteJobEventMethod = 
              io.grpc.MethodDescriptor.<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent, com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "produceEvents.EventProducer", "ProduceDeleteJobEvent"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new EventProducerMethodDescriptorSupplier("ProduceDeleteJobEvent"))
                  .build();
          }
        }
     }
     return getProduceDeleteJobEventMethod;
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
    public void producePostNewJobEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProducePostNewJobEventMethod(), responseObserver);
    }

    /**
     */
    public void produceAcceptStudentApplicationEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProduceAcceptStudentApplicationEventMethod(), responseObserver);
    }

    /**
     */
    public void produceRemoveStudentApplicationEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProduceRemoveStudentApplicationEventMethod(), responseObserver);
    }

    /**
     */
    public void produceDeleteJobEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getProduceDeleteJobEventMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getProducePostNewJobEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent,
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>(
                  this, METHODID_PRODUCE_POST_NEW_JOB_EVENT)))
          .addMethod(
            getProduceAcceptStudentApplicationEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent,
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>(
                  this, METHODID_PRODUCE_ACCEPT_STUDENT_APPLICATION_EVENT)))
          .addMethod(
            getProduceRemoveStudentApplicationEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent,
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>(
                  this, METHODID_PRODUCE_REMOVE_STUDENT_APPLICATION_EVENT)))
          .addMethod(
            getProduceDeleteJobEventMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent,
                com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>(
                  this, METHODID_PRODUCE_DELETE_JOB_EVENT)))
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
    public void producePostNewJobEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProducePostNewJobEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void produceAcceptStudentApplicationEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProduceAcceptStudentApplicationEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void produceRemoveStudentApplicationEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProduceRemoveStudentApplicationEventMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void produceDeleteJobEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getProduceDeleteJobEventMethod(), getCallOptions()), request, responseObserver);
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
    public com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse producePostNewJobEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent request) {
      return blockingUnaryCall(
          getChannel(), getProducePostNewJobEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse produceAcceptStudentApplicationEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent request) {
      return blockingUnaryCall(
          getChannel(), getProduceAcceptStudentApplicationEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse produceRemoveStudentApplicationEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent request) {
      return blockingUnaryCall(
          getChannel(), getProduceRemoveStudentApplicationEventMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse produceDeleteJobEvent(com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent request) {
      return blockingUnaryCall(
          getChannel(), getProduceDeleteJobEventMethod(), getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> producePostNewJobEvent(
        com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getProducePostNewJobEventMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> produceAcceptStudentApplicationEvent(
        com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getProduceAcceptStudentApplicationEventMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> produceRemoveStudentApplicationEvent(
        com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getProduceRemoveStudentApplicationEventMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse> produceDeleteJobEvent(
        com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent request) {
      return futureUnaryCall(
          getChannel().newCall(getProduceDeleteJobEventMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_PRODUCE_POST_NEW_JOB_EVENT = 0;
  private static final int METHODID_PRODUCE_ACCEPT_STUDENT_APPLICATION_EVENT = 1;
  private static final int METHODID_PRODUCE_REMOVE_STUDENT_APPLICATION_EVENT = 2;
  private static final int METHODID_PRODUCE_DELETE_JOB_EVENT = 3;

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
        case METHODID_PRODUCE_POST_NEW_JOB_EVENT:
          serviceImpl.producePostNewJobEvent((com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.PostNewJobEvent) request,
              (io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>) responseObserver);
          break;
        case METHODID_PRODUCE_ACCEPT_STUDENT_APPLICATION_EVENT:
          serviceImpl.produceAcceptStudentApplicationEvent((com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.AcceptStudentApplicationEvent) request,
              (io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>) responseObserver);
          break;
        case METHODID_PRODUCE_REMOVE_STUDENT_APPLICATION_EVENT:
          serviceImpl.produceRemoveStudentApplicationEvent((com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.RemoveStudentApplicationEvent) request,
              (io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.SagaResponse>) responseObserver);
          break;
        case METHODID_PRODUCE_DELETE_JOB_EVENT:
          serviceImpl.produceDeleteJobEvent((com.cseiu.passnet.saga.recruitmentsaga.ProduceEvents.DeleteJobEvent) request,
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
              .addMethod(getProducePostNewJobEventMethod())
              .addMethod(getProduceAcceptStudentApplicationEventMethod())
              .addMethod(getProduceRemoveStudentApplicationEventMethod())
              .addMethod(getProduceDeleteJobEventMethod())
              .build();
        }
      }
    }
    return result;
  }
}
