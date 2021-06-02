// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: bank.proto

package com.bok.bank.integration.grpc;

/**
 * Protobuf type {@code AuthorizationResponse}
 */
public  final class AuthorizationResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:AuthorizationResponse)
    AuthorizationResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use AuthorizationResponse.newBuilder() to construct.
  private AuthorizationResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private AuthorizationResponse() {
    authorizationId_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new AuthorizationResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private AuthorizationResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            authorized_ = input.readBool();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            authorizationId_ = s;
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.bok.bank.integration.grpc.BankProto.internal_static_AuthorizationResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bok.bank.integration.grpc.BankProto.internal_static_AuthorizationResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.bok.bank.integration.grpc.AuthorizationResponse.class, com.bok.bank.integration.grpc.AuthorizationResponse.Builder.class);
  }

  public static final int AUTHORIZED_FIELD_NUMBER = 1;
  private boolean authorized_;
  /**
   * <code>bool authorized = 1;</code>
   * @return The authorized.
   */
  public boolean getAuthorized() {
    return authorized_;
  }

  public static final int AUTHORIZATIONID_FIELD_NUMBER = 2;
  private volatile java.lang.Object authorizationId_;
  /**
   * <code>string authorizationId = 2;</code>
   * @return The authorizationId.
   */
  public java.lang.String getAuthorizationId() {
    java.lang.Object ref = authorizationId_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      authorizationId_ = s;
      return s;
    }
  }
  /**
   * <code>string authorizationId = 2;</code>
   * @return The bytes for authorizationId.
   */
  public com.google.protobuf.ByteString
      getAuthorizationIdBytes() {
    java.lang.Object ref = authorizationId_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      authorizationId_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (authorized_ != false) {
      output.writeBool(1, authorized_);
    }
    if (!getAuthorizationIdBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, authorizationId_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (authorized_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, authorized_);
    }
    if (!getAuthorizationIdBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, authorizationId_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.bok.bank.integration.grpc.AuthorizationResponse)) {
      return super.equals(obj);
    }
    com.bok.bank.integration.grpc.AuthorizationResponse other = (com.bok.bank.integration.grpc.AuthorizationResponse) obj;

    if (getAuthorized()
        != other.getAuthorized()) return false;
    if (!getAuthorizationId()
        .equals(other.getAuthorizationId())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + AUTHORIZED_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getAuthorized());
    hash = (37 * hash) + AUTHORIZATIONID_FIELD_NUMBER;
    hash = (53 * hash) + getAuthorizationId().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.bok.bank.integration.grpc.AuthorizationResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.bok.bank.integration.grpc.AuthorizationResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code AuthorizationResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:AuthorizationResponse)
      com.bok.bank.integration.grpc.AuthorizationResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bok.bank.integration.grpc.BankProto.internal_static_AuthorizationResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bok.bank.integration.grpc.BankProto.internal_static_AuthorizationResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.bok.bank.integration.grpc.AuthorizationResponse.class, com.bok.bank.integration.grpc.AuthorizationResponse.Builder.class);
    }

    // Construct using com.bok.bank.integration.grpc.AuthorizationResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      authorized_ = false;

      authorizationId_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bok.bank.integration.grpc.BankProto.internal_static_AuthorizationResponse_descriptor;
    }

    @java.lang.Override
    public com.bok.bank.integration.grpc.AuthorizationResponse getDefaultInstanceForType() {
      return com.bok.bank.integration.grpc.AuthorizationResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.bok.bank.integration.grpc.AuthorizationResponse build() {
      com.bok.bank.integration.grpc.AuthorizationResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.bok.bank.integration.grpc.AuthorizationResponse buildPartial() {
      com.bok.bank.integration.grpc.AuthorizationResponse result = new com.bok.bank.integration.grpc.AuthorizationResponse(this);
      result.authorized_ = authorized_;
      result.authorizationId_ = authorizationId_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.bok.bank.integration.grpc.AuthorizationResponse) {
        return mergeFrom((com.bok.bank.integration.grpc.AuthorizationResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.bok.bank.integration.grpc.AuthorizationResponse other) {
      if (other == com.bok.bank.integration.grpc.AuthorizationResponse.getDefaultInstance()) return this;
      if (other.getAuthorized() != false) {
        setAuthorized(other.getAuthorized());
      }
      if (!other.getAuthorizationId().isEmpty()) {
        authorizationId_ = other.authorizationId_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.bok.bank.integration.grpc.AuthorizationResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.bok.bank.integration.grpc.AuthorizationResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private boolean authorized_ ;
    /**
     * <code>bool authorized = 1;</code>
     * @return The authorized.
     */
    public boolean getAuthorized() {
      return authorized_;
    }
    /**
     * <code>bool authorized = 1;</code>
     * @param value The authorized to set.
     * @return This builder for chaining.
     */
    public Builder setAuthorized(boolean value) {
      
      authorized_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool authorized = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearAuthorized() {
      
      authorized_ = false;
      onChanged();
      return this;
    }

    private java.lang.Object authorizationId_ = "";
    /**
     * <code>string authorizationId = 2;</code>
     * @return The authorizationId.
     */
    public java.lang.String getAuthorizationId() {
      java.lang.Object ref = authorizationId_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        authorizationId_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string authorizationId = 2;</code>
     * @return The bytes for authorizationId.
     */
    public com.google.protobuf.ByteString
        getAuthorizationIdBytes() {
      java.lang.Object ref = authorizationId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        authorizationId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string authorizationId = 2;</code>
     * @param value The authorizationId to set.
     * @return This builder for chaining.
     */
    public Builder setAuthorizationId(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      authorizationId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string authorizationId = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearAuthorizationId() {
      
      authorizationId_ = getDefaultInstance().getAuthorizationId();
      onChanged();
      return this;
    }
    /**
     * <code>string authorizationId = 2;</code>
     * @param value The bytes for authorizationId to set.
     * @return This builder for chaining.
     */
    public Builder setAuthorizationIdBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      authorizationId_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:AuthorizationResponse)
  }

  // @@protoc_insertion_point(class_scope:AuthorizationResponse)
  private static final com.bok.bank.integration.grpc.AuthorizationResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.bok.bank.integration.grpc.AuthorizationResponse();
  }

  public static com.bok.bank.integration.grpc.AuthorizationResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<AuthorizationResponse>
      PARSER = new com.google.protobuf.AbstractParser<AuthorizationResponse>() {
    @java.lang.Override
    public AuthorizationResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new AuthorizationResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<AuthorizationResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<AuthorizationResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.bok.bank.integration.grpc.AuthorizationResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

