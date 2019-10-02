/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: sample.proto

package org.springframework.protobuf;

/**
 * Protobuf type {@code Msg}
 */
public final class Msg extends
        com.google.protobuf.GeneratedMessage
        implements MsgOrBuilder {
    // Use Msg.newBuilder() to construct.
    private Msg(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
        super(builder);
        this.unknownFields = builder.getUnknownFields();
    }

    private Msg(boolean noInit) {
        this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance();
    }

    private static final Msg defaultInstance;

    public static Msg getDefaultInstance() {
        return defaultInstance;
    }

    public Msg getDefaultInstanceForType() {
        return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
        return this.unknownFields;
    }

    private Msg(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        initFields();
        @SuppressWarnings("unused")
        int mutable_bitField0_ = 0;
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
                    default: {
                        if (!parseUnknownField(input, unknownFields,
                                extensionRegistry, tag)) {
                            done = true;
                        }
                        break;
                    }
                    case 10: {
                        bitField0_ |= 0x00000001;
                        foo_ = input.readBytes();
                        break;
                    }
                    case 18: {
                        org.springframework.protobuf.SecondMsg.Builder subBuilder = null;
                        if (((bitField0_ & 0x00000002) == 0x00000002)) {
                            subBuilder = blah_.toBuilder();
                        }
                        blah_ = input.readMessage(org.springframework.protobuf.SecondMsg.PARSER, extensionRegistry);
                        if (subBuilder != null) {
                            subBuilder.mergeFrom(blah_);
                            blah_ = subBuilder.buildPartial();
                        }
                        bitField0_ |= 0x00000002;
                        break;
                    }
                }
            }
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(this);
        } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(
                    e.getMessage()).setUnfinishedMessage(this);
        } finally {
            this.unknownFields = unknownFields.build();
            makeExtensionsImmutable();
        }
    }

    public static final com.google.protobuf.Descriptors.Descriptor
    getDescriptor() {
        return org.springframework.protobuf.OuterSample.internal_static_Msg_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
    internalGetFieldAccessorTable() {
        return org.springframework.protobuf.OuterSample.internal_static_Msg_fieldAccessorTable
                .ensureFieldAccessorsInitialized(
                        org.springframework.protobuf.Msg.class, org.springframework.protobuf.Msg.Builder.class);
    }

    public static com.google.protobuf.Parser<Msg> PARSER =
            new com.google.protobuf.AbstractParser<Msg>() {
                public Msg parsePartialFrom(
                        com.google.protobuf.CodedInputStream input,
                        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                        throws com.google.protobuf.InvalidProtocolBufferException {
                    return new Msg(input, extensionRegistry);
                }
            };

    @java.lang.Override
    public com.google.protobuf.Parser<Msg> getParserForType() {
        return PARSER;
    }

    private int bitField0_;
    // optional string foo = 1;
    public static final int FOO_FIELD_NUMBER = 1;
    private java.lang.Object foo_;

    /**
     * <code>optional string foo = 1;</code>
     */
    public boolean hasFoo() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
    }

    /**
     * <code>optional string foo = 1;</code>
     */
    public java.lang.String getFoo() {
        java.lang.Object ref = foo_;
        if (ref instanceof java.lang.String) {
            return (java.lang.String) ref;
        } else {
            com.google.protobuf.ByteString bs =
                    (com.google.protobuf.ByteString) ref;
            java.lang.String s = bs.toStringUtf8();
            if (bs.isValidUtf8()) {
                foo_ = s;
            }
            return s;
        }
    }

    /**
     * <code>optional string foo = 1;</code>
     */
    public com.google.protobuf.ByteString
    getFooBytes() {
        java.lang.Object ref = foo_;
        if (ref instanceof java.lang.String) {
            com.google.protobuf.ByteString b =
                    com.google.protobuf.ByteString.copyFromUtf8(
                            (java.lang.String) ref);
            foo_ = b;
            return b;
        } else {
            return (com.google.protobuf.ByteString) ref;
        }
    }

    // optional .SecondMsg blah = 2;
    public static final int BLAH_FIELD_NUMBER = 2;
    private org.springframework.protobuf.SecondMsg blah_;

    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public boolean hasBlah() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
    }

    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public org.springframework.protobuf.SecondMsg getBlah() {
        return blah_;
    }

    /**
     * <code>optional .SecondMsg blah = 2;</code>
     */
    public org.springframework.protobuf.SecondMsgOrBuilder getBlahOrBuilder() {
        return blah_;
    }

    private void initFields() {
        foo_ = "";
        blah_ = org.springframework.protobuf.SecondMsg.getDefaultInstance();
    }

    private byte memoizedIsInitialized = -1;

    public final boolean isInitialized() {
        byte isInitialized = memoizedIsInitialized;
        if (isInitialized != -1) return isInitialized == 1;

        memoizedIsInitialized = 1;
        return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
            throws java.io.IOException {
        getSerializedSize();
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
            output.writeBytes(1, getFooBytes());
        }
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
            output.writeMessage(2, blah_);
        }
        getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;

    public int getSerializedSize() {
        int size = memoizedSerializedSize;
        if (size != -1) return size;

        size = 0;
        if (((bitField0_ & 0x00000001) == 0x00000001)) {
            size += com.google.protobuf.CodedOutputStream
                    .computeBytesSize(1, getFooBytes());
        }
        if (((bitField0_ & 0x00000002) == 0x00000002)) {
            size += com.google.protobuf.CodedOutputStream
                    .computeMessageSize(2, blah_);
        }
        size += getUnknownFields().getSerializedSize();
        memoizedSerializedSize = size;
        return size;
    }

    private static final long serialVersionUID = 0L;

    @java.lang.Override
    protected java.lang.Object writeReplace()
            throws java.io.ObjectStreamException {
        return super.writeReplace();
    }

    public static org.springframework.protobuf.Msg parseFrom(
            com.google.protobuf.ByteString data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static org.springframework.protobuf.Msg parseFrom(
            com.google.protobuf.ByteString data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static org.springframework.protobuf.Msg parseFrom(byte[] data)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data);
    }

    public static org.springframework.protobuf.Msg parseFrom(
            byte[] data,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
        return PARSER.parseFrom(data, extensionRegistry);
    }

    public static org.springframework.protobuf.Msg parseFrom(java.io.InputStream input)
            throws java.io.IOException {
        return PARSER.parseFrom(input);
    }

    public static org.springframework.protobuf.Msg parseFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return PARSER.parseFrom(input, extensionRegistry);
    }

    public static org.springframework.protobuf.Msg parseDelimitedFrom(java.io.InputStream input)
            throws java.io.IOException {
        return PARSER.parseDelimitedFrom(input);
    }

    public static org.springframework.protobuf.Msg parseDelimitedFrom(
            java.io.InputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }

    public static org.springframework.protobuf.Msg parseFrom(
            com.google.protobuf.CodedInputStream input)
            throws java.io.IOException {
        return PARSER.parseFrom(input);
    }

    public static org.springframework.protobuf.Msg parseFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws java.io.IOException {
        return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() {
        return Builder.create();
    }

    public Builder newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder(org.springframework.protobuf.Msg prototype) {
        return newBuilder().mergeFrom(prototype);
    }

    public Builder toBuilder() {
        return newBuilder(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
            com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /**
     * Protobuf type {@code Msg}
     */
    public static final class Builder extends
            com.google.protobuf.GeneratedMessage.Builder<Builder>
            implements org.springframework.protobuf.MsgOrBuilder {
        public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
            return org.springframework.protobuf.OuterSample.internal_static_Msg_descriptor;
        }

        protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
            return org.springframework.protobuf.OuterSample.internal_static_Msg_fieldAccessorTable
                    .ensureFieldAccessorsInitialized(
                            org.springframework.protobuf.Msg.class, org.springframework.protobuf.Msg.Builder.class);
        }

        // Construct using org.springframework.protobuf.Msg.newBuilder()
        private Builder() {
            maybeForceBuilderInitialization();
        }

        private Builder(
                com.google.protobuf.GeneratedMessage.BuilderParent parent) {
            super(parent);
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
                getBlahFieldBuilder();
            }
        }

        private static Builder create() {
            return new Builder();
        }

        public Builder clear() {
            super.clear();
            foo_ = "";
            bitField0_ = (bitField0_ & ~0x00000001);
            if (blahBuilder_ == null) {
                blah_ = org.springframework.protobuf.SecondMsg.getDefaultInstance();
            } else {
                blahBuilder_.clear();
            }
            bitField0_ = (bitField0_ & ~0x00000002);
            return this;
        }

        public Builder clone() {
            return create().mergeFrom(buildPartial());
        }

        public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
            return org.springframework.protobuf.OuterSample.internal_static_Msg_descriptor;
        }

        public org.springframework.protobuf.Msg getDefaultInstanceForType() {
            return org.springframework.protobuf.Msg.getDefaultInstance();
        }

        public org.springframework.protobuf.Msg build() {
            org.springframework.protobuf.Msg result = buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        public org.springframework.protobuf.Msg buildPartial() {
            org.springframework.protobuf.Msg result = new org.springframework.protobuf.Msg(this);
            int from_bitField0_ = bitField0_;
            int to_bitField0_ = 0;
            if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
                to_bitField0_ |= 0x00000001;
            }
            result.foo_ = foo_;
            if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
                to_bitField0_ |= 0x00000002;
            }
            if (blahBuilder_ == null) {
                result.blah_ = blah_;
            } else {
                result.blah_ = blahBuilder_.build();
            }
            result.bitField0_ = to_bitField0_;
            onBuilt();
            return result;
        }

        public Builder mergeFrom(com.google.protobuf.Message other) {
            if (other instanceof org.springframework.protobuf.Msg) {
                return mergeFrom((org.springframework.protobuf.Msg) other);
            } else {
                super.mergeFrom(other);
                return this;
            }
        }

        public Builder mergeFrom(org.springframework.protobuf.Msg other) {
            if (other == org.springframework.protobuf.Msg.getDefaultInstance()) return this;
            if (other.hasFoo()) {
                bitField0_ |= 0x00000001;
                foo_ = other.foo_;
                onChanged();
            }
            if (other.hasBlah()) {
                mergeBlah(other.getBlah());
            }
            this.mergeUnknownFields(other.getUnknownFields());
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        public Builder mergeFrom(
                com.google.protobuf.CodedInputStream input,
                com.google.protobuf.ExtensionRegistryLite extensionRegistry)
                throws java.io.IOException {
            org.springframework.protobuf.Msg parsedMessage = null;
            try {
                parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
            } catch (com.google.protobuf.InvalidProtocolBufferException e) {
                parsedMessage = (org.springframework.protobuf.Msg) e.getUnfinishedMessage();
                throw e;
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
            return this;
        }

        private int bitField0_;

        // optional string foo = 1;
        private java.lang.Object foo_ = "";

        /**
         * <code>optional string foo = 1;</code>
         */
        public boolean hasFoo() {
            return ((bitField0_ & 0x00000001) == 0x00000001);
        }

        /**
         * <code>optional string foo = 1;</code>
         */
        public java.lang.String getFoo() {
            java.lang.Object ref = foo_;
            if (!(ref instanceof java.lang.String)) {
                java.lang.String s = ((com.google.protobuf.ByteString) ref)
                        .toStringUtf8();
                foo_ = s;
                return s;
            } else {
                return (java.lang.String) ref;
            }
        }

        /**
         * <code>optional string foo = 1;</code>
         */
        public com.google.protobuf.ByteString
        getFooBytes() {
            java.lang.Object ref = foo_;
            if (ref instanceof String) {
                com.google.protobuf.ByteString b =
                        com.google.protobuf.ByteString.copyFromUtf8(
                                (java.lang.String) ref);
                foo_ = b;
                return b;
            } else {
                return (com.google.protobuf.ByteString) ref;
            }
        }

        /**
         * <code>optional string foo = 1;</code>
         */
        public Builder setFoo(
                java.lang.String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            foo_ = value;
            onChanged();
            return this;
        }

        /**
         * <code>optional string foo = 1;</code>
         */
        public Builder clearFoo() {
            bitField0_ = (bitField0_ & ~0x00000001);
            foo_ = getDefaultInstance().getFoo();
            onChanged();
            return this;
        }

        /**
         * <code>optional string foo = 1;</code>
         */
        public Builder setFooBytes(
                com.google.protobuf.ByteString value) {
            if (value == null) {
                throw new NullPointerException();
            }
            bitField0_ |= 0x00000001;
            foo_ = value;
            onChanged();
            return this;
        }

        // optional .SecondMsg blah = 2;
        private org.springframework.protobuf.SecondMsg blah_ = org.springframework.protobuf.SecondMsg.getDefaultInstance();
        private com.google.protobuf.SingleFieldBuilder<
                org.springframework.protobuf.SecondMsg, org.springframework.protobuf.SecondMsg.Builder,
                org.springframework.protobuf.SecondMsgOrBuilder> blahBuilder_;

        /**
         * <code>optional .SecondMsg blah = 2;</code>
         */
        public boolean hasBlah() {
            return ((bitField0_ & 0x00000002) == 0x00000002);
        }

        /**
         * <code>optional .SecondMsg blah = 2;</code>
         */
        public org.springframework.protobuf.SecondMsg getBlah() {
            if (blahBuilder_ == null) {
                return blah_;
            } else {
                return blahBuilder_.getMessage();
            }
        }

        /**
         * <code>optional .SecondMsg blah = 2;</code>
         */
        public Builder setBlah(org.springframework.protobuf.SecondMsg value) {
            if (blahBuilder_ == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                blah_ = value;
                onChanged();
            } else {
                blahBuilder_.setMessage(value);
            }
            bitField0_ |= 0x00000002;
            return this;
        }

        /**
         * <code>optional .SecondMsg blah = 2;</code>
         */
        public Builder setBlah(
                org.springframework.protobuf.SecondMsg.Builder builderForValue) {
            if (blahBuilder_ == null) {
                blah_ = builderForValue.build();
                onChanged();
            } else {
                blahBuilder_.setMessage(builderForValue.build());
            }
            bitField0_ |= 0x00000002;
            return this;
        }

        /**
         * <code>optional .SecondMsg blah = 2;</code>
         */
        public Builder mergeBlah(org.springframework.protobuf.SecondMsg value) {
            if (blahBuilder_ == null) {
                if (((bitField0_ & 0x00000002) == 0x00000002) &&
                        blah_ != org.springframework.protobuf.SecondMsg.getDefaultInstance()) {
                    blah_ =
                            org.springframework.protobuf.SecondMsg.newBuilder(blah_).mergeFrom(value).buildPartial();
                } else {
                    blah_ = value;
                }
                onChanged();
            } else {
                blahBuilder_.mergeFrom(value);
            }
            bitField0_ |= 0x00000002;
            return this;
        }

        /**
         * <code>optional .SecondMsg blah = 2;</code>
         */
        public Builder clearBlah() {
            if (blahBuilder_ == null) {
                blah_ = org.springframework.protobuf.SecondMsg.getDefaultInstance();
                onChanged();
            } else {
                blahBuilder_.clear();
            }
            bitField0_ = (bitField0_ & ~0x00000002);
            return this;
        }

        /**
         * <code>optional .SecondMsg blah = 2;</code>
         */
        public org.springframework.protobuf.SecondMsg.Builder getBlahBuilder() {
            bitField0_ |= 0x00000002;
            onChanged();
            return getBlahFieldBuilder().getBuilder();
        }

        /**
         * <code>optional .SecondMsg blah = 2;</code>
         */
        public org.springframework.protobuf.SecondMsgOrBuilder getBlahOrBuilder() {
            if (blahBuilder_ != null) {
                return blahBuilder_.getMessageOrBuilder();
            } else {
                return blah_;
            }
        }

        /**
         * <code>optional .SecondMsg blah = 2;</code>
         */
        private com.google.protobuf.SingleFieldBuilder<
                org.springframework.protobuf.SecondMsg, org.springframework.protobuf.SecondMsg.Builder,
                org.springframework.protobuf.SecondMsgOrBuilder>
        getBlahFieldBuilder() {
            if (blahBuilder_ == null) {
                blahBuilder_ = new com.google.protobuf.SingleFieldBuilder<>(
                        blah_,
                        getParentForChildren(),
                        isClean());
                blah_ = null;
            }
            return blahBuilder_;
        }

        // @@protoc_insertion_point(builder_scope:Msg)
    }

    static {
        defaultInstance = new Msg(true);
        defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:Msg)
}

