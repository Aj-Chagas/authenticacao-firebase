package br.com.anderson.chagas.autheticationdesire.model

class Session {
    companion object StaticSession {
        private var instance: Session? = null
        var email: String? = null

        fun getInstance(): Session {
            if (instance == null) {
                return Session()
            }
            return instance as Session
        }

        fun clearInstance() {
            instance = null
        }
    }


}