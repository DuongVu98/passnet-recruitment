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
    comments = "Source: example.proto")
public final class GreetingGrpc {

  private GreetingGrpc() {}

  public static final String SERVICE_NAME = "helloWorld.Greeting";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest,
      com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest.class,
      responseType = com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest,
      com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest, com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> getSayHelloMethod;
    if ((getSayHelloMethod = GreetingGrpc.getSayHelloMethod) == null) {
      synchronized (GreetingGrpc.class) {
        if ((getSayHelloMethod = GreetingGrpc.getSayHelloMethod) == null) {
          GreetingGrpc.getSayHelloMethod = getSayHelloMethod = 
              io.grpc.MethodDescriptor.<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest, com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloWorld.Greeting", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingMethodDescriptorSupplier("SayHello"))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest,
      com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> getSayGoodByeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayGoodBye",
      requestType = com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest.class,
      responseType = com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest,
      com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> getSayGoodByeMethod() {
    io.grpc.MethodDescriptor<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest, com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> getSayGoodByeMethod;
    if ((getSayGoodByeMethod = GreetingGrpc.getSayGoodByeMethod) == null) {
      synchronized (GreetingGrpc.class) {
        if ((getSayGoodByeMethod = GreetingGrpc.getSayGoodByeMethod) == null) {
          GreetingGrpc.getSayGoodByeMethod = getSayGoodByeMethod = 
              io.grpc.MethodDescriptor.<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest, com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "helloWorld.Greeting", "SayGoodBye"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new GreetingMethodDescriptorSupplier("SayGoodBye"))
                  .build();
          }
        }
     }
     return getSayGoodByeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GreetingStub newStub(io.grpc.Channel channel) {
    return new GreetingStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GreetingBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new GreetingBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GreetingFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new GreetingFutureStub(channel);
  }

  /**
   */
  public static abstract class GreetingImplBase implements io.grpc.BindableService {

    /**
     */
    public void sayHello(com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     */
    public void sayGoodBye(com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSayGoodByeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest,
                com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            getSayGoodByeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest,
                com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply>(
                  this, METHODID_SAY_GOOD_BYE)))
          .build();
    }
  }

  /**
   */
  public static final class GreetingStub extends io.grpc.stub.AbstractStub<GreetingStub> {
    private GreetingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingStub(channel, callOptions);
    }

    /**
     */
    public void sayHello(com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sayGoodBye(com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest request,
        io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayGoodByeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GreetingBlockingStub extends io.grpc.stub.AbstractStub<GreetingBlockingStub> {
    private GreetingBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply sayHello(com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply sayGoodBye(com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayGoodByeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GreetingFutureStub extends io.grpc.stub.AbstractStub<GreetingFutureStub> {
    private GreetingFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private GreetingFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GreetingFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new GreetingFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> sayHello(
        com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply> sayGoodBye(
        com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayGoodByeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SAY_GOOD_BYE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GreetingImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GreetingImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply>) responseObserver);
          break;
        case METHODID_SAY_GOOD_BYE:
          serviceImpl.sayGoodBye((com.cseiu.passnet.saga.recruitmentsaga.Example.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.cseiu.passnet.saga.recruitmentsaga.Example.HelloReply>) responseObserver);
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

  private static abstract class GreetingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GreetingBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.cseiu.passnet.saga.recruitmentsaga.Example.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("Greeting");
    }
  }

  private static final class GreetingFileDescriptorSupplier
      extends GreetingBaseDescriptorSupplier {
    GreetingFileDescriptorSupplier() {}
  }

  private static final class GreetingMethodDescriptorSupplier
      extends GreetingBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GreetingMethodDescriptorSupplier(String methodName) {
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
      synchronized (GreetingGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GreetingFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getSayGoodByeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
