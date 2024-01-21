JBOSS_HOME=

if [ -z "$JBOSS_HOME" ]
then
  echo "Impostare la variabile JBOSS_HOME nello script"
  exit 1
fi

echo "embed-server --server-config=standalone-full.xml --std-out=echo" > /tmp/temp-script.cli
echo "/subsystem=messaging-activemq/server=default/jms-queue=LogQueue:add(entries=[\"java:/jms/queue/LogQueue\"])" >> /tmp/temp-script.cli
echo "/subsystem=security/security-domain=userroles:add" >> /tmp/temp-script.cli
echo "/subsystem=security/security-domain=userroles:write-attribute(name=cache-type, value=\"default\")" >> /tmp/temp-script.cli
echo "/subsystem=security/security-domain=userroles/authentication=classic:add(login-modules=[{code=UsersRoles, flag=required,module-options={\"usersProperties\"=>\"file://$JBOSS_HOME/standalone/configuration/users.properties\", \"rolesProperties\"=>\"file://$JBOSS_HOME/standalone/configuration/roles.properties\"}}]" >> /tmp/temp-script.cli
echo "/subsystem=logging/logger=com.example.javaee.exame.example:add(level=DEBUG)" >> /tmp/temp-script.cli
echo "/subsystem=logging/pattern-formatter=PATTERN:write-attribute(name=pattern, value=\"%K{level}%d{HH:mm:ss,SSS} %-5p [%c] (%t) %X{transactionId} %X{user} %s%e%n\")" >> /tmp/temp-script.cli
echo "/subsystem=logging/pattern-formatter=COLOR-PATTERN:write-attribute(name=pattern, value=\"%K{level}%d{HH:mm:ss,SSS} %-5p [%c] (%t) %X{transactionId} %X{user} %s%e%n\")" >> /tmp/temp-script.cli
echo "stop-embedded-server" >> /tmp/temp-script.cli

$JBOSS_HOME/bin/jboss-cli.sh --file=/tmp/temp-script.cli

rm /tmp/temp-script.cli