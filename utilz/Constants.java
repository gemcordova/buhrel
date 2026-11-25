package utilz;

public class Constants {
    public static class Direction {
        public static final int UP = 0;
        public static final int LEFT = 1;
        public static final int DOWN = 2;
        public static final int RIGHT = 3;
    }

    public static class PlayerConstants {
        public static final int RUN = 0;
        public static final int RUN_ATTACK = 1;
        public static final int KICK = 2;
        public static final int JUMP = 3;
        public static final int IDLE = 4;
        public static final int HURT = 5;
        public static final int DOUBLE_JUMP = 6;
        public static final int DEATH = 7;
        public static final int ATTACK_3 = 8;
        public static final int ATTACK_2 = 9;
        public static final int ATTACK_1 = 10;

        public static int GetSpriteAmount(int player_action) {
        switch (player_action) {
                case RUN:
                case RUN_ATTACK:
                case KICK:
                case DOUBLE_JUMP:
                case DEATH:
                case ATTACK_1:
                    return 6;
                case JUMP:
                case IDLE:
                    return 4;
                case HURT:
                    return 2;
                case ATTACK_3:
                case ATTACK_2:
                    return 8;
                default:
                    return 1;
            }
        }
    }
}
