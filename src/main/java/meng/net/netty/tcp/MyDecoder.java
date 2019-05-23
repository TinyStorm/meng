package meng.net.netty.tcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ctx.channel().writeAndFlush("serve My Decoder receive message");
        byte[] data = new byte[in.readableBytes()];
        in.readBytes(data);
        out.add(new String(data));
    }
}
