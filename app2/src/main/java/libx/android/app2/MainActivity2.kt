package libx.android.app2

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.opengl.Matrix
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import libx.android.app2.utils.GLUtil
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

/**
 * MainActivity的升级版，使用
 */
class MainActivity2 : AppCompatActivity() {
    private val TAG = "MainActivity="
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val glSurfaceView = GLSurfaceView(this)
//        glSurfaceView.setEGLContextClientVersion(2);
//        glSurfaceView.setRenderer(DemoRenderer2())
//        setContentView(glSurfaceView)

        setContentView(R.layout.activity_main2)
        val glSurfaceView = findViewById<GLSurfaceView>(R.id.glsv)
        glSurfaceView.setEGLContextClientVersion(2)
        glSurfaceView.setRenderer(DemoRenderer2())
    }


    inner class DemoRenderer : GLSurfaceView.Renderer {
        override fun onSurfaceCreated(unused: GL10?, config: EGLConfig?) {
            Log.d(TAG, "onSurfaceCreated")
            // 设置个红色背景
            GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f)
        }

        override fun onDrawFrame(unused: GL10) {
            Log.d(TAG, "onDrawFrame")
            // Redraw background color 重绘背景
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
        }

        override fun onSurfaceChanged(unused: GL10, width: Int, height: Int) {
            Log.d(TAG, "onSurfaceChanged")
            // 设置绘图的窗口(可以理解成在画布上划出一块区域来画图)
            GLES20.glViewport(100, 100, width, height)
        }
    }

    inner class DemoRenderer2 : GLSurfaceView.Renderer {
        private var mGlTriangle: GLTriangle? = null


        private val mMVPMatrix = FloatArray(16) //变换矩阵（投影矩阵*相机矩阵的结果，最终要传递给顶点着色器）

        private val mProjectionMatrix = FloatArray(16) //投影矩阵

        private val mViewMatrix = FloatArray(16) //相机位置矩阵

        override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
            mGlTriangle = GLTriangle()
            // 设置个红色背景
            GLES20.glClearColor(1.0f, 0.0f, 0.0f, 1.0f)
        }


        override fun onSurfaceChanged(gl: GL10?, width: Int, height: Int) {
            // 设置绘图的窗口(可以理解成在画布上划出一块区域来画图)
            GLES20.glViewport(0, 0, width, height)
            Log.d(TAG, "onSurfaceChanged，width：$width, height: $height")
            /**投影和相机视图相关 */
            val ratio = width.toFloat() / height
            //设置正交投影
            Matrix.orthoM(mProjectionMatrix, 0, -ratio, ratio, -1f, 2f, 3f, 7f);
//            Matrix.orthoM(
//                mProjectionMatrix, 0, 0f, width.toFloat(),
//                height.toFloat(), 0f, (-width).toFloat(), width.toFloat()
//            ) //这个投影会跟屏幕坐标关联上
            //设置透视投影（观察点越远，视图越小），这个投影矩阵被应用于对象坐标在onDrawFrame（）方法中
//            Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1f, 2f, 3f, 7f);

            //设置相机位置
            Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, 7f, 0f, 0f, 0f, 0f, 1.0f, 1.0f)
            //计算变换矩阵,最终mMVPMatrix要传递给顶点着色器
            Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0)
        }


        override fun onDrawFrame(gl: GL10) {
            // Redraw background color 重绘背景
            GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT)
            mGlTriangle?.setMvpMatrix(mMVPMatrix)
            mGlTriangle?.draw()
            Log.d(TAG, "onDrawFrame,mMVPMatrix: ${mMVPMatrix.asList()}")
        }
    }

    class GLTriangle {
        // 顶点着色器的脚本
        var vertexShaderCode = "uniform mat4 uMVPMatrix;" +  //接收传入的转换矩阵
                " attribute vec4 vPosition;" +  //接收传入的顶点
                " void main() {" +
                "  gl_Position = uMVPMatrix * vPosition;" +  //矩阵变换计算之后的位置
                " }"

        // 顶点着色器的脚本
//        var vertexShaderCode = " attribute vec4 vPosition;" +  // 应用程序传入顶点着色器的顶点位置
//                " void main() {" +
//                "     gl_Position = vPosition;" +  // 此次绘制此顶点位置
//                " }"

        // 片元着色器的脚本
        var fragmentShaderCode = " precision mediump float;" +  // 设置工作精度
                " uniform vec4 vColor;" +  // 接收从顶点着色器过来的顶点颜色数据
                " void main() {" +
                "     gl_FragColor = vColor;" +  // 给此片元的填充色
                " }"
        private val vertexBuffer //顶点坐标数据要转化成FloatBuffer格式
                : FloatBuffer

        //顶点个数，计算得出
        private val vertexCount = triangleCoords.size / COORDS_PER_VERTEX

        //一个顶点有3个float，一个float是4个字节，所以一个顶点要12字节
        private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex

        //三角形的颜色数组，rgba
        private val mColor = floatArrayOf(
            0.0f, 1.0f, 0.0f, 1.0f
        )

        //当前绘制的顶点位置句柄
        private var vPosition = 0

        //片元着色器颜色句柄
        private var vColor = 0

        //这个可以理解为一个OpenGL程序句柄
        private val mProgram: Int


        //变换矩阵句柄
        private var mMVPMatrixHandle = 0

        //变换矩阵，提供set方法
        private var mvpMatrix = FloatArray(16)
        fun setMvpMatrix(mvpMatrix: FloatArray) {
            this.mvpMatrix = mvpMatrix
//            this.mvpMatrix = floatArrayOf(
//                1f, 0f, 0f, 0f,
//                0f, 1f, 0f, 0f,
//                0f, 0f, 1f, 0f,
//                0f, 0f, 0f, 1f
//            )
        }

        init {
            /** 1、数据转换，顶点坐标数据float类型转换成OpenGL格式FloatBuffer，int和short同理 */
            vertexBuffer = GLUtil.floatArray2FloatBuffer(triangleCoords)
            /** 2、加载编译顶点着色器和片元着色器 */
            val vertexShader: Int = GLUtil.loadShader(
                GLES20.GL_VERTEX_SHADER,
                vertexShaderCode
            )
            val fragmentShader: Int = GLUtil.loadShader(
                GLES20.GL_FRAGMENT_SHADER,
                fragmentShaderCode
            )
            /** 3、创建空的OpenGL ES程序，并把着色器添加进去 */
            mProgram = GLES20.glCreateProgram()

            // 添加顶点着色器到程序中
            GLES20.glAttachShader(mProgram, vertexShader)

            // 添加片段着色器到程序中
            GLES20.glAttachShader(mProgram, fragmentShader)
            /** 4、链接程序 */
            GLES20.glLinkProgram(mProgram)
        }

        fun draw() {

            // 将程序添加到OpenGL ES环境
            GLES20.glUseProgram(mProgram)
            /***在什么位置显示什么颜色 */

            // 获取顶点着色器的位置的句柄（这里可以理解为当前绘制的顶点位置）
            vPosition = GLES20.glGetAttribLocation(mProgram, "vPosition")

            // 启用顶点属性
            GLES20.glEnableVertexAttribArray(vPosition)

            //准备三角形坐标数据
            GLES20.glVertexAttribPointer(
                vPosition, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer
            )

            // 获取片段着色器的vColor属性
            vColor = GLES20.glGetUniformLocation(mProgram, "vColor")

            // 设置绘制三角形的颜色
            GLES20.glUniform4fv(vColor, 1, mColor, 0)

//            // 获取变换矩阵的句柄
            mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
            // 将投影和视图转换传递给着色器，可以理解为给顶点着色器中uMVPMatrix这个变量赋值为mvpMatrix
            GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);

            // 绘制三角形
            GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount)

            // 禁用顶点数组
            GLES20.glDisableVertexAttribArray(vPosition)
        }

        companion object {
            // 数组中每3个值作为一个坐标点
            const val COORDS_PER_VERTEX = 3

            //三角形的坐标数组
            var triangleCoords = floatArrayOf(
                0.0f, 0.5f, 0.0f,  // top
                -0.5f, -0.5f, 0.0f,  // bottom left
                0.5f, -0.5f, 0.0f // bottom right
            )
        }
    }

}


