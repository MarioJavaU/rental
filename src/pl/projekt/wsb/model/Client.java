package pl.projekt.wsb.model;

import java.util.Objects;

public abstract class Client {
        private String firstName;
        private String lastName;
        private String pesel;

        public Client(String firstName, String lastName, String pesel) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.pesel = pesel;
        }


    @Override
    public String toString() {
        return firstName + " " +
                lastName +  " " +
                pesel;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(pesel, client.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, pesel);
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(final String pesel) {
        this.pesel = pesel;
    }

    public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }


    }

