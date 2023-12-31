package libx.android.app2.utils;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class GLUtil {
    /**
     * float 数组转换成FloatBuffer，OpenGL才能使用
     *
     * @param arr
     * @return
     */
    public static FloatBuffer floatArray2FloatBuffer(float[] arr) {
        FloatBuffer mBuffer;
        // 初始化ByteBuffer，长度为arr数组的长度*4，因为一个int占4个字节
        ByteBuffer qbb = ByteBuffer.allocateDirect(arr.length * 4);
        // 数组排列用nativeOrder
        qbb.order(ByteOrder.nativeOrder());
        mBuffer = qbb.asFloatBuffer();
        mBuffer.put(arr);
        mBuffer.position(0);
        return mBuffer;
    }

    public static int loadShader(int shaderType, String source) {
        // 创造顶点着色器类型(GLES20.GL_VERTEX_SHADER)
        // 或者是片段着色器类型 (GLES20.GL_FRAGMENT_SHADER)
        int shader = 0;
        try {
            shader = GLES20.glCreateShader(shaderType);
            // 添加上面编写的着色器代码并编译它
            GLES20.glShaderSource(shader, source);
            GLES20.glCompileShader(shader);
        } catch (Throwable e){
            e.printStackTrace();
        }


        return shader;
    }
}
