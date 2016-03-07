#!/usr/bin/env bash

VAGRANT_DIR=/vagrant/contrib/kafka9/vagrant
DIST_DIR=${VAGRANT_DIR}/heron-ubuntu
HERON_CONF_PATH=${VAGRANT_DIR}/conf/mesos_scheduler.conf
DEFN_TMP_DIR=${DIST_DIR}/defn-tmp

if [[ $# -lt 3 ]] ; then
    echo 'USAGE: ./submit-custom-topology-mesos.sh <jar_file_name> <main_class_name> <topology_name> <args>'
    exit 1
fi

pushd ${DIST_DIR}
    ./heron-0.1.0-SNAPSHOT/bin/heron-cli2 submit "" ${DIST_DIR}/topologies/$1 $2 $3 ${@:4} --config-loader com.twitter.heron.scheduler.util.DefaultConfigLoader --config-path ${HERON_CONF_PATH} --tmp-dir ${DEFN_TMP_DIR}
popd
