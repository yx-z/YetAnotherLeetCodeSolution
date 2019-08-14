package easy

class Sol929 {

    fun numUniqueEmails(emails: Array<String>) = emails.map {
        var (local, domain) = it.split("@")
        local = local.replace(".", "")
        val idx = local.indexOf('+')
        if (idx >= 0) {
            local = local.substring(0, idx)
        }
        "$local@$domain"
    }.toSet().size
}