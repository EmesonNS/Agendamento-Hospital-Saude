package com.hospitalsaude.scheduling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_admin")
public class Admin extends User{
}
