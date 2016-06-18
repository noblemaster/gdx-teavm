/*-------------------------------------------------------
 * This file was automatically generated by XpeCodeGen
 *
 * Dont make changes to this file
 *-------------------------------------------------------*/
package com.badlogic.gdx.physics.bullet.linearmath;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.bullet.BulletBase;

/** @author xpenatan */
public class btMotionState extends BulletBase {

    public btMotionState() {
        resetObj(createNative(), true);
    }

    @Override
    protected void delete() {
		long addr = cPointer;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("var cobj=Bullet.wrapPointer(addr,Bullet.MyMotionState);Bullet.destroy(cobj);",this);
    }

    private long createNative() {
		Matrix4 tmpMat=btTransform.tmp_param1;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("var self=this;var jsMotionState=new Bullet.MyMotionState();jsMotionState.getWorldTransform=function(worldTrans){var worldTrans=Bullet.wrapPointer(worldTrans,Bullet.btTransform);self.$getWorldTransform___com_badlogic_gdx_math_Matrix4$void(tmpMat);var origin=worldTrans.getOrigin();var matrix3x3=worldTrans.getBasis();var row0=matrix3x3.getRow(0);var row1=matrix3x3.getRow(1);var row2=matrix3x3.getRow(2);row0.setValue(tmpMat.$$$val[0],tmpMat.$$$val[4],tmpMat.$$$val[8]);row1.setValue(tmpMat.$$$val[1],tmpMat.$$$val[5],tmpMat.$$$val[9]);row2.setValue(tmpMat.$$$val[2],tmpMat.$$$val[6],tmpMat.$$$val[10]);origin.setValue(tmpMat.$$$val[12],tmpMat.$$$val[13],tmpMat.$$$val[14]);};jsMotionState.setWorldTransform=function(worldTrans){var worldTrans=Bullet.wrapPointer(worldTrans,Bullet.btTransform);var origin=worldTrans.getOrigin();var matrix3x3=worldTrans.getBasis();var row0=matrix3x3.getRow(0);var row1=matrix3x3.getRow(1);var row2=matrix3x3.getRow(2);tmpMat.$$$val[0]=row0.x();tmpMat.$$$val[1]=row1.x();tmpMat.$$$val[2]=row2.x();tmpMat.$$$val[3]=0;tmpMat.$$$val[4]=row0.y();tmpMat.$$$val[5]=row1.y();tmpMat.$$$val[6]=row2.y();tmpMat.$$$val[7]=0;tmpMat.$$$val[8]=row0.z();tmpMat.$$$val[9]=row1.z();tmpMat.$$$val[10]=row2.z();tmpMat.$$$val[11]=0;tmpMat.$$$val[12]=origin.x();tmpMat.$$$val[13]=origin.y();tmpMat.$$$val[14]=origin.z();tmpMat.$$$val[15]=1.0;self.$setWorldTransform___com_badlogic_gdx_math_Matrix4$void(tmpMat);};",this);
		return com.dragome.commons.javascript.ScriptHelper.evalLong("Bullet.getPointer(jsMotionState);",this);
    }

	protected void cacheObj() {
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("this.$$$jsObj=Bullet.wrapPointer(this.$$$cPointer,Bullet.MyMotionState);",this);
	}

    /**
	 * Called to initialize body position. Modify worldTrans.
	 */
    public void getWorldTransform(Matrix4 worldTrans) {
    }

    /**
	 * Called when rigid body change position. Update your render matrix with worldTrans.
	 */
    public void setWorldTransform(Matrix4 worldTrans) {
    }
}