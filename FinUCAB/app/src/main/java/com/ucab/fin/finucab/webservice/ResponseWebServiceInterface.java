package com.ucab.fin.finucab.webservice;

/**
 * Created by Ramon on 24-May-17.
 */

public interface ResponseWebServiceInterface {

    void obtuvoCorrectamente( Object response );

    void noObtuvoCorrectamente( Object error );

}
