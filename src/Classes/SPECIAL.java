package Classes;

public class SPECIAL {
    protected int
        Str,
        Per,
        End,
        Cha,
        Int,
        Agl,
        Lck;
    private static double MUTATION = .10; //% de mutacion

    public SPECIAL(int S, int P, int E, int C, int I, int A, int L) {
        Str = S;
        Per = P;
        End = E;
        Cha = C;
        Int = I;
        Agl = A;
        Lck = L;
    }

    public SPECIAL(SPECIAL a, SPECIAL b){
        this.setStr(merge(a.Str, b.Str));
        this.setPer(merge(a.Per, b.Per));
        this.setEnd(merge(a.End, b.End));
        this.setCha(merge(a.Cha, b.Cha));
        this.setInt(merge(a.Int, b.Int));
        this.setAgl(merge(a.Agl, b.Agl));
        this.setLck(merge(a.Lck, b.Lck));
    }

    public int getStr() {
        return Str;
    }

    public void setStr(int str) {
        if(str<0 || str>10)
            str = 0;
        Str = str;
    }

    public int getPer() {
        return Per;
    }

    public void setPer(int per) {
        if(per<0 || per>10)
            per = 0;
        Per = per;
    }

    public int getEnd() {
        return End;
    }

    public void setEnd(int end) {
        if(end<0 || end>10)
            end = 0;
        End = end;
    }

    public int getCha() {
        return Cha;
    }

    public void setCha(int cha) {
        if(cha<0 || cha>10)
            cha = 0;
        Cha = cha;
    }

    public int getInt() {
        return Int;
    }

    public void setInt(int anInt) {
        if(anInt<0 || anInt>10)
            anInt = 0;
        Int = anInt;
    }

    public int getAgl() {
        return Agl;
    }

    public void setAgl(int agl) {
        if(agl<0 || agl>10)
            agl = 0;
        Agl = agl;
    }

    public int getLck() {
        return Lck;
    }

    public void setLck(int lck) {
        if(lck<0 || lck>10)
            lck = 0;
        Lck = lck;
    }

    private int merge(int a, int b){
        if(Math.random() < MUTATION)
            return (int) (Math.random() * 10); //return random int 0-10
        int min, max;
        if(a<b) {
            min = a;
            max = b;
        } else{
            min = b;
            max = a;
        }
        return (int) ((Math.random() * (max - min)) + min); //return random int min-max
    }

    @Override
    public String toString() {
        return    "Str=" + Str +
                ", Per=" + Per +
                ", End=" + End +
                ", Cha=" + Cha +
                ", Int=" + Int +
                ", Agl=" + Agl +
                ", Lck=" + Lck;
    }
}
