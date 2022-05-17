package com.umg.trains;

public class Train implements TrainInterface{

    private int hp;
    private int armor;
    private int power;
    private int damage;
    private int atackSpeed;
    private int image;


    public Train(int hp, int armour, int power, int damage, int attackSpeed, int image) {
        this.hp = hp;
        this.armor = armour;
        this.power = power;
        this.damage = damage;
        this.atackSpeed = attackSpeed;
        this.image = image;
    }


    @Override
    public int getDefense() {
        return hp*armor;
    }

    @Override
    public int getOffence() {
        return power*damage;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAtackSpeed() {
        return atackSpeed;
    }

    public void setAtackSpeed(int atackSpeed) {
        this.atackSpeed = atackSpeed;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

}
