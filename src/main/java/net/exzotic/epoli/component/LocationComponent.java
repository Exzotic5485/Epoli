package net.exzotic.epoli.component;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

public class LocationComponent {
    private double x, y, z;
    private float pitch, yaw;
    private String saveid;
    private Identifier dimension;

    public LocationComponent(double x, double y, double z, float pitch, float yaw, Identifier dimension, String saveid) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.saveid = saveid;
        this.dimension = dimension;
    }

    public LocationComponent(Vec3d pos, float pitch, float yaw, Identifier dimension, String saveid) {
        this.x = pos.x;
        this.y = pos.y;
        this.z = pos.z;
        this.pitch = pitch;
        this.yaw = yaw;
        this.saveid = saveid;
        this.dimension = dimension;
    }

    public static LocationComponent readFromNbt(NbtCompound tag) {
        return new LocationComponent(
                tag.getDouble("x"),
                tag.getDouble("y"),
                tag.getDouble("z"),
                tag.getFloat("pitch"),
                tag.getFloat("yaw"),
                Identifier.tryParse(tag.getString("dimension")),
                tag.getString("saveid")
        );
    }

    public void writeToNbt(NbtCompound tag) {
        tag.putDouble("x", x);
        tag.putDouble("y", y);
        tag.putDouble("z", z);
        tag.putFloat("pitch", pitch);
        tag.putFloat("yaw", yaw);
        tag.putString("dimension", dimension.toString());
        tag.putString("saveid", saveid);
    }

    public double getX()  { return x; }
    public double getY()  { return y; }
    public double getZ()  { return z; }
    public float getPitch()  { return pitch; }
    public float getYaw()    { return yaw;   }
    public String getSaveId()   { return saveid;  }
    public Identifier getDimID() { return dimension; }
}
