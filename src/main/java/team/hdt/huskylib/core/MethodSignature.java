package team.hdt.huskylib.core;

public class MethodSignature {
    public String funcName, srgName, obfName, funcDesc;

    public MethodSignature(String funcName, String srgName, String obfName, String funcDesc) {
        this.funcName = funcName;
        this.srgName = srgName;
        this.obfName = obfName;
        this.funcDesc = funcDesc;
    }

    public MethodSignature(String... values) {
        this.funcName = values[0];
        this.srgName = values[1];
        this.obfName = values[2];
        this.funcDesc = values[3];
    }

    @Override
    public String toString() {
        return "Names [" + funcName + ", " + srgName + ", " + obfName + "] Descriptor " + funcDesc;
    }

}
