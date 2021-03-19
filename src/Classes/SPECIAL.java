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
    private static double MUTATION = .05; //% de mutacion

    public SPECIAL(int S, int P, int E, int C, int I, int A, int L) {
        Str = S;
        Per = P;
        End = E;
        Cha = C;
        Int = I;
        Agl = A;
        Lck = L;
    }

    public SPECIAL(SPECIAL mom, SPECIAL dad){
        this.setStr(merge(mom.Str, dad.Str));
        this.setPer(merge(mom.Per, dad.Per));
        this.setEnd(merge(mom.End, dad.End));
        this.setCha(merge(mom.Cha, dad.Cha));
        this.setInt(merge(mom.Int, dad.Int));
        this.setAgl(merge(mom.Agl, dad.Agl));
        this.setLck(merge(mom.Lck, dad.Lck));
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
        if(Math.random() > MUTATION)
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
        return "Classes.SPECIAL{" +
                "Str=" + Str +
                ", Per=" + Per +
                ", End=" + End +
                ", Cha=" + Cha +
                ", Int=" + Int +
                ", Agl=" + Agl +
                ", Lck=" + Lck +
                '}';
    }
}
