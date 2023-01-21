enum EnumExample {
    TEACHER(1),
    FIRE_PERSON(2),
    CHEF(3);

    int id;

    EnumExample(int id) {
        this.id = id;
    }
}

class Scratch {
    public static void main(String[] args) {
        for (EnumExample value : EnumExample.values()) {
            switch (value) {
                case TEACHER:
                    System.out.println("A teacher teaches");
                    break;
                case FIRE_PERSON:
                    System.out.println("A fire-person extinguishes fire!");
                    break;
            }
        }
    }
}

