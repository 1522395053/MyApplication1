package libx.android.app2.opengl;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * GLES20.glVertexAttribPointer参数意义说明
 * <p>
 * <a href="https://www.likecs.com/show-308470070.html">...</a>
 *
 */
public class VertexData {
    private static final int FLOAT_SIZE_BYTES = 4;//一个float数据占四个字节
    private static final int TRIANGLE_VERTICES_DATA_STRIDE_BYTES = 5 * FLOAT_SIZE_BYTES;//每5个元素表示一个顶点
    private static final int TRIANGLE_VERTICES_DATA_POS_OFFSET = 0;
    private static final int TRIANGLE_VERTICES_DATA_UV_OFFSET = 3;

    //下面的这三段代码的作用是指定一个三角形的三个顶点和纹理的UV向量，每个顶点由x,y,z三个基向量标识，每个纹理由U,V两个基向量标志，所有的数据开始都保存在数组mTriangleVerticesData之中，通过下面的三段代码实现了顶点处理。
    private final float[] mTriangleVerticesData = {
            // X, Y, Z, U, V
            -1.0f, -0.5f, 0, -0.5f, 0.0f,
            1.0f, -0.5f, 0, 1.5f, -0.0f,
            0.0f,  1.11803399f, 0, 0.5f,  1.61803399f };

    private FloatBuffer mTriangleVertices;
    private int maPositionHandle;
    private int maTextureHandle;









    private void draw(){
        mTriangleVertices = ByteBuffer.allocateDirect(mTriangleVerticesData.length
                * FLOAT_SIZE_BYTES).order(ByteOrder.nativeOrder()).asFloatBuffer();
        mTriangleVertices.put(mTriangleVerticesData).position(0);


        mTriangleVertices.position(TRIANGLE_VERTICES_DATA_POS_OFFSET);//从索引0开始取数据
        GLES20.glVertexAttribPointer(maPositionHandle, 3, GLES20.GL_FLOAT, false,//取3个数据
                TRIANGLE_VERTICES_DATA_STRIDE_BYTES, mTriangleVertices);//跳转20个字节位(5个数据)再取另外3个数据，这是实现块状数据存储的关键，很多函数里都有这个参数，通常写作int stride
        checkGlError("glVertexAttribPointer maPosition");
        mTriangleVertices.position(TRIANGLE_VERTICES_DATA_UV_OFFSET);//从索引3开始取数据
        GLES20.glEnableVertexAttribArray(maPositionHandle);
        checkGlError("glEnableVertexAttribArray maPositionHandle");
        GLES20.glVertexAttribPointer(maTextureHandle, 2, GLES20.GL_FLOAT, false,//取两个数据U,V
                TRIANGLE_VERTICES_DATA_STRIDE_BYTES, mTriangleVertices);//到此基本明晰了如何块状存储数据
        checkGlError("glVertexAttribPointer maTextureHandle");
        GLES20.glEnableVertexAttribArray(maTextureHandle);
        checkGlError("glEnableVertexAttribArray maTextureHandle");
    }

    /**
     * ADD
     * @param info
     */
    private void checkGlError(String info){

    }
}
