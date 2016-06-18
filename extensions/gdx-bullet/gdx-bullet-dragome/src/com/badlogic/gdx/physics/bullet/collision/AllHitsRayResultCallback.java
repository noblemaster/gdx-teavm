/*-------------------------------------------------------
 * This file was automatically generated by XpeCodeGen
 *
 * Dont make changes to this file
 *-------------------------------------------------------*/
package com.badlogic.gdx.physics.bullet.collision;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.linearmath.btScalarArray;
import com.badlogic.gdx.physics.bullet.linearmath.btVector3;
import com.badlogic.gdx.physics.bullet.linearmath.btVector3Array;

/** @author xpenatan */
public class AllHitsRayResultCallback extends RayResultCallback {

    btCollisionObjectArray objArray = new btCollisionObjectArray(0, false);

    btVector3Array hitnormalArray = new btVector3Array(0, false);

    btVector3Array hitpointArray = new btVector3Array(0, false);

    btScalarArray hitfractionArray = new btScalarArray(0, false);

    public AllHitsRayResultCallback(Vector3 rayFromWorld, Vector3 rayToWorld) {
        resetObj(createNative(rayFromWorld.x, rayFromWorld.y, rayFromWorld.z, rayToWorld.x, rayToWorld.y, rayToWorld.z, false), true);
    }

    public AllHitsRayResultCallback(Vector3 rayFromWorld, Vector3 rayToWorld, boolean toOverride) {
        resetObj(createNative(rayFromWorld.x, rayFromWorld.y, rayFromWorld.z, rayToWorld.x, rayToWorld.y, rayToWorld.z, toOverride), true);
    }

    private long createNative(float x1, float y1, float z1, float x2, float y2, float z2, boolean overriden) {
		LocalRayResult tmpLocal = RayResultCallback.tmpLocalRes;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("var from=new Bullet.btVector3(x1,y1,z1);var to=new Bullet.btVector3(x2,y2,z2);var cobj=new Bullet.MyAllHitsRayResultCallback(from,to);var self=this;cobj.addSingleResult=function(rayResult,normalInWorldSpace) {tmpLocal.$resetObj___long__boolean$void(rayResult,false);return self.$addSingleResult___com_badlogic_gdx_physics_bullet_collision_LocalRayResult__boolean$float(tmpLocal,normalInWorldSpace);};",this);
		return com.dragome.commons.javascript.ScriptHelper.evalLong("Bullet.getPointer(cobj);",this);
    }

	protected void cacheObj() {
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("this.$$$jsObj=Bullet.wrapPointer(this.$$$cPointer,Bullet.MyAllHitsRayResultCallback);",this);
	}

    @Override
    protected void delete() {
		long addr = cPointer;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("var cobj=Bullet.wrapPointer(addr,Bullet.MyAllHitsRayResultCallback);Bullet.destroy(cobj);",this);
		objArray.resetObj(0, false);
		hitnormalArray.resetObj(0, false);
		hitpointArray.resetObj(0, false);
		hitfractionArray.resetObj(0, false);
    }

    public btCollisionObjectArray getCollisionObjects() {
		checkPointer();
		long ptr = 0;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("ptr=Bullet.getPointer(this.$$$jsObj.get_m_collisionObjects());",this);
		objArray.resetObj(ptr, false);
		return objArray;
    }

    public void getRayFromWorld(Vector3 out) {
		checkPointer();
		float x=0,y=0,z=0;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("var vec=this.$$$jsObj.get_m_rayFromWorld();x=vec.x();z=vec.y();y=vec.z();",this);
		out.set(x,y,z);
    }

    public void setRayFromWorld(Vector3 value) {
		checkPointer();
		float x=value.x,y=value.y,z=value.z;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("var vec=Bullet.MyTemp.prototype.btVec3();vec.setValue(x,y,z);this.$$$jsObj.set_m_rayFromWorld(vec);",this);
    }

    public void getRayToWorld(Vector3 out) {
		checkPointer();
		float x=0,y=0,z=0;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("var vec=this.$$$jsObj.get_m_rayToWorld();x=vec.x();z=vec.y();y=vec.z();",this);
		out.set(x,y,z);
    }

    public void setRayToWorld(Vector3 value) {
		checkPointer();
		float x=value.x,y=value.y,z=value.z;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("var vec=Bullet.MyTemp.prototype.btVec3();vec.setValue(x,y,z);this.$$$jsObj.set_m_rayToWorld(vec);",this);
    }

    public btVector3Array getHitNormalWorld() {
		checkPointer();
		long ptr = 0;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("ptr=Bullet.getPointer(this.$$$jsObj.get_m_hitNormalWorld());",this);
		hitnormalArray.resetObj(ptr, false);
		return hitnormalArray;
    }

    public btVector3Array getHitPointWorld() {
		checkPointer();
		long ptr = 0;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("ptr=Bullet.getPointer(this.$$$jsObj.get_m_hitPointWorld());",this);
		hitpointArray.resetObj(ptr, false);
		return hitpointArray;
    }

    public btScalarArray getHitFractions() {
		checkPointer();
		long ptr = 0;
		com.dragome.commons.javascript.ScriptHelper.evalNoResult("ptr=Bullet.getPointer(this.$$$jsObj.get_m_hitFractions());",this);
		hitfractionArray.resetObj(ptr, false);
		return hitfractionArray;
    }

    public float addSingleResult(LocalRayResult rayResult, boolean normalInWorldSpace) {
		checkPointer();
		Object locRay = rayResult.jsObj;
		return com.dragome.commons.javascript.ScriptHelper.evalFloat("this.$$$jsObj.addSingleResultSuper(locRay,normalInWorldSpace);",this);
    }

    @Override
    public void clear() {
        super.clear();
        getHitNormalWorld().resize(0);
        getHitPointWorld().resize(0);
        getHitFractions().resize(0);
        getCollisionObjects().resize(0);
    }
}
